package com.example.BicycleManagement.service.impl

import com.example.BicycleManagement.configuration.JwtProperties
import com.example.BicycleManagement.exception.NotFoundException
import com.example.BicycleManagement.model.Customer
import com.example.BicycleManagement.model.RefreshToken
import com.example.BicycleManagement.repository.CustomerRepository
import com.example.BicycleManagement.repository.RefreshTokenRepository
import com.example.BicycleManagement.service.RefreshTokenService
import com.example.BicycleManagement.service.TokenService
import org.springframework.stereotype.Service
import java.time.Instant
import java.util.*


@Service
class RefreshTokenServiceImpl(
    private val jwtProperties: JwtProperties,
    private val refreshTokenRepository: RefreshTokenRepository,
    private val customerRepository: CustomerRepository,
    private val tokenService: TokenService

)  : RefreshTokenService{
    override fun createRefreshToken(customer: Customer) : RefreshToken {
        val refreshToken = RefreshToken(
            customer = customer,
            token = UUID.randomUUID().toString(),
            expiryDate = Instant.now().plusMillis(jwtProperties.refreshTokenExpiration)
        )
        refreshTokenRepository.save(refreshToken).let { return it }
    }

    override fun verifyExpiration(date: Instant): Boolean {
        return date.isAfter(Instant.now())
    }

    override fun getNewToken(refreshToken: String): String {
        val token = refreshTokenRepository.findByToken(refreshToken).orElseThrow { NotFoundException("Refresh token not found") }
        if (!verifyExpiration(token.expiryDate!!)) {
            refreshTokenRepository.delete(token)
            throw RuntimeException("Refresh token has expired. Please make a new login.")
        }

        return tokenService.generate(token.customer!!, Date(System.currentTimeMillis() + jwtProperties.accessTokenExpiration))
    }

    override fun getRefreshToken(email: String): String {
        val customer = customerRepository.findByEmail(email).orElseThrow {
            NotFoundException("User not found with email $email")
        }
        val refreshToken = refreshTokenRepository.findByCustomer(customer).orElseGet { createRefreshToken(customer) }
        return refreshToken.token!!
    }
}