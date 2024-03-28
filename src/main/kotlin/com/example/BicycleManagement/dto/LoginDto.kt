package com.example.BicycleManagement.dto

import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min

data class LoginDto(
    var email : String,
    @field:Min(value = 8 , message = "The password need greater than 7")
    @field:Max(value = 16 , message = "The password need less than 17")
    var password : String
)