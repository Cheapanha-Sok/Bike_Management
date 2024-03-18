package com.example.BicycleManagement.dto

import java.time.LocalTime

data class NotFoundDto(
    val code :Int ? =null ,
    val message :String?=null ,
    val localTime: LocalTime? =null
)
