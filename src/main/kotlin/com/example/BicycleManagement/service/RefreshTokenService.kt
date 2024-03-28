package com.example.BicycleManagement.service

import com.example.BicycleManagement.model.Customer
import com.example.BicycleManagement.model.RefreshToken
import java.time.Instant

interface RefreshTokenService {
    fun createRefreshToken(customer: Customer) : RefreshToken
    fun verifyExpiration(date: Instant): Boolean
    fun getNewToken(refreshToken :String) : String
    fun getRefreshToken(email :String) :String
}