package com.example.BicycleManagement.controller

import com.example.BicycleManagement.base.response.MessageResponse
import com.example.BicycleManagement.dto.*
import com.example.BicycleManagement.service.AuthService
import com.example.BicycleManagement.util.constants.Constant
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.ModelAndView


@RestController
@RequestMapping("${Constant.MAIN_URL}auth")
class AuthController(private val authService: AuthService) {

    @PostMapping("/register")
    fun register(@RequestBody registerDto: RegisterDto) : MessageResponse{
        return authService.register(registerDto.username , registerDto.email , registerDto.password)
    }
    @PostMapping("/login")
    fun login(@RequestBody loginDto: LoginDto) :LoginResponse{
        return authService.login(loginDto.email , loginDto.password)
    }
    @PostMapping("/refreshToken")
    fun refreshToken(@RequestBody refreshTokenRequest: RefreshTokenRequest) : AccessTokenResponse{
        return authService.getToken(refreshTokenRequest.refreshToken!!)
    }
    @GetMapping("/confirm")
    fun confirm(@RequestParam("email") email: String) : ModelAndView {
        return authService.verifyEmail(email)
    }
}