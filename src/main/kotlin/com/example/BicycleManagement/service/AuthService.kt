package com.example.BicycleManagement.service

import com.example.BicycleManagement.base.response.MessageResponse
import com.example.BicycleManagement.dto.AccessTokenResponse
import com.example.BicycleManagement.dto.LoginResponse
import org.springframework.web.servlet.ModelAndView

interface AuthService {
    fun register(name :String , email:String , password :String) : MessageResponse
    fun login(email :String , password :String) : LoginResponse
    fun getToken(refreshToken :String) : AccessTokenResponse
    fun verifyEmail(email :String): ModelAndView
}