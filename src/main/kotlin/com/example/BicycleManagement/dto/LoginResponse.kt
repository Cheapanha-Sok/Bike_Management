package com.example.BicycleManagement.dto

data class LoginResponse(
    var role : List<String>,
    var accessToken :String,
    var refreshToken : String,
    var username :String,
)
