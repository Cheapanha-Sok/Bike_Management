package com.example.BicycleManagement.service.impl

import com.example.BicycleManagement.base.response.MessageResponse
import com.example.BicycleManagement.configuration.JwtProperties
import com.example.BicycleManagement.dto.AccessTokenResponse
import com.example.BicycleManagement.dto.LoginResponse
import com.example.BicycleManagement.exception.NotFoundException
import com.example.BicycleManagement.model.Customer
import com.example.BicycleManagement.repository.CustomerRepository
import com.example.BicycleManagement.repository.RoleRepository
import com.example.BicycleManagement.service.AuthService
import com.example.BicycleManagement.service.RefreshTokenService
import com.example.BicycleManagement.service.TokenService
import com.example.BicycleManagement.service.UserDetailsService
import jakarta.mail.MessagingException
import jakarta.mail.internet.MimeMessage
import org.springframework.beans.factory.annotation.Value
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.web.servlet.ModelAndView
import java.util.Date


@Service
class AuthServiceImpl(
    private val customerRepository: CustomerRepository,
    private val refreshTokenService: RefreshTokenService,
    private val roleRepository: RoleRepository,
    private val passwordEncoder: PasswordEncoder,
    private val tokenService: TokenService,
    private val jwtProperties: JwtProperties,
    private val authManager: AuthenticationManager,
    private val userDetailsService: UserDetailsService,
    private val mailSender: JavaMailSender,
) : AuthService {
    override fun register(name: String, email: String, password: String): MessageResponse {
        val role = roleRepository.findByName("USER").orElseThrow { NotFoundException("Role not found with User") }
        val customer = Customer().apply {
            this.name = name
            this.email = email
            this.password = passwordEncoder.encode(password)
            this.role = role
        }
        customerRepository.save(customer).let { sentEmail(it.email!!) }
            .let { return MessageResponse() }

    }
    override fun login(email: String, password: String): LoginResponse {
        authManager.authenticate(
            UsernamePasswordAuthenticationToken(
                email, password
            )
        )
        val customer = userDetailsService.loadUserByUsername(email)
        val accessToken: String = generateAccessToken(customer!!)
        val refreshToken: String = refreshTokenService.getRefreshToken(email)

        return LoginResponse(
            accessToken = accessToken,
            refreshToken = refreshToken,
            role = customer.authorities.map { it.authority },
            username = customer.username,
        )
    }

    override fun getToken(refreshToken: String): AccessTokenResponse {
        val newToken = refreshTokenService.getNewToken(refreshToken)
        return AccessTokenResponse(
            newToken
        )
    }

    override fun verifyEmail(email :String ) :ModelAndView {
        val customer = customerRepository.findByEmail(email)
        if (customer.isPresent){
            if (customer.get().isEnabled){
                return ModelAndView("Validated")
            }else{
                customer.get().isEnabled = true
                customerRepository.save(customer.get())
                return ModelAndView("Validated")
            }
        }
        return ModelAndView("EmailNotFound")
    }
    private fun generateAccessToken(user: UserDetails) = tokenService.generate(
        userDetails = user,
        expirationDate = Date(System.currentTimeMillis() + jwtProperties.accessTokenExpiration)
    )

    @Value("\${spring.mail.username}")
    private val emailFrom: String? = null

    private fun sentEmail(toEmail: String) {
        try {
            val mimeMessage: MimeMessage = mailSender.createMimeMessage()
            val helper = MimeMessageHelper(mimeMessage, true, "UTF-8")
            helper.setFrom(emailFrom!!)
            helper.setTo(toEmail)
            helper.setSubject("Confirm your email")
            val url = "http://localhost:8080/api/v1/auth/confirm?email=$toEmail"
            helper.setText(buildEmailSender(url), true)
            mailSender.send(mimeMessage)
        } catch (e: MessagingException) {
            throw IllegalStateException("Failed to send email", e)
        }
    }

    private fun buildEmailSender(url :String): String =
        "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Email Confirmation</title>\n" +
                "    <style>\n" +
                "        body {\n" +
                "            font-family: Arial, sans-serif;\n" +
                "            margin: 0;\n" +
                "            padding: 0;\n" +
                "            background-color: #f4f4f4;\n" +
                "        }\n" +
                "        .container {\n" +
                "            max-width: 600px;\n" +
                "            margin: 0 auto;\n" +
                "            padding: 20px;\n" +
                "            background-color: #fff;\n" +
                "            border-radius: 8px;\n" +
                "            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\n" +
                "        }\n" +
                "        h1 {\n" +
                "            text-align: center;\n" +
                "            color: #333;\n" +
                "        }\n" +
                "        p {\n" +
                "            margin-bottom: 20px;\n" +
                "            line-height: 1.6;\n" +
                "        }\n" +
                "        .btn {\n" +
                "            display: inline-block;\n" +
                "            padding: 10px 20px;\n" +
                "            background-color: #007bff;\n" +
                "            color: #fff;\n" +
                "            text-decoration: none;\n" +
                "            border-radius: 5px;\n" +
                "        }\n" +
                "        .btn:hover {\n" +
                "            background-color: #0056b3;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div class=\"container\">\n" +
                "        <h1>Confirm Your Email</h1>\n" +
                "        <p>Thank you for registering! To complete your registration, please click the button below to confirm your email address.</p>\n" +
                "        <a href=\"$url\" class=\"btn\">Confirm Email</a>\n" +
                "        <p>If you did not register on our website, you can ignore this email.</p>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>\n"
}