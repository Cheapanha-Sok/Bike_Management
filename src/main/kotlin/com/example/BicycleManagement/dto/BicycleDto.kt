package com.example.BicycleManagement.dto

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDate

data class BicycleDto(
    var id :Long? = null,
    var name: String ? =null,
    var category: String ? =null,
    @JsonFormat(pattern = "yyyy-mm-dd")
    var manufactureDate : LocalDate? =null,
    var status : Boolean ? = null,
    var quantity : Int ? = null,
    var price : Float? = null
)
