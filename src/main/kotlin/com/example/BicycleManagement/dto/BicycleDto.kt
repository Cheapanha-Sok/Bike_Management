package com.example.BicycleManagement.dto

import com.fasterxml.jackson.annotation.JsonFormat
import java.sql.Date

data class BicycleDto(
    var id: Long? = null,
    var name: String? = null,
    var category: String? = null,
    @field:JsonFormat(pattern = "yyyy-MM-dd")
    var manufactureDate: Date? = null,
    var status: Boolean? = null,
    var quantity: Int? = null,
    var price: Double? = null
)
