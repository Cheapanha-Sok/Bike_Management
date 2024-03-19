package com.example.BicycleManagement.dto

import java.util.Date

data class BicycleDto(
    var id :Long,
    var name : String,
    var category: String,
    var manufactureDate : Date,
    var status : Boolean,
    var quantity : Int,
    var price : Float?
)
