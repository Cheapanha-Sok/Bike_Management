package com.example.BicycleManagement.dto

import com.example.BicycleManagement.model.Category
import com.example.BicycleManagement.model.Supplier
import com.fasterxml.jackson.annotation.JsonFormat
import java.sql.Date
import java.time.LocalDate

data class ImportBike(
    var name : String,
    var unitPrice : Double,
    var sellPrice : Double,
    var categoryId : Long,
    var supplierId : Long,
    var createAt: LocalDate?,
    @field:JsonFormat(pattern = "yyyy-MM-dd")
    var manufactureDate: Date,
    var quantity: Int,
    var category: Category?=null,
    var supplier: Supplier?=null,
    var totalQuantity : Int?=null,
)