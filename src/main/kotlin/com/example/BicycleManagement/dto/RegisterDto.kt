package com.example.BicycleManagement.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotEmpty
data class RegisterDto(
    @field:NotEmpty(message = "username cannot be empty")
    var username :String,
    @field:Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    @field:NotEmpty(message = "Email cannot be empty")
    var email : String,
    @field:Min(value = 8 , message = "The password need greater than 7")
    @field:Max(value = 16 , message = "The password need less than 17")
    @field:NotEmpty(message = "The password is require")
    var password :String
)
