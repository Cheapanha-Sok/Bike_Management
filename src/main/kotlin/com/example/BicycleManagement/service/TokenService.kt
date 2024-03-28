package com.example.BicycleManagement.service

import io.jsonwebtoken.Claims
import org.springframework.security.core.userdetails.UserDetails
import java.util.*

interface TokenService {

    fun generate(userDetails: UserDetails, expirationDate: Date, additionalClaims: Map<String , Any> = emptyMap()):String
    fun getAllClaims(token :String) : Claims
    fun extractEmail(token :String?) :String
    fun isExpired(token :String) :Boolean
    fun isValid(token :String , userDetails: UserDetails) :Boolean
}