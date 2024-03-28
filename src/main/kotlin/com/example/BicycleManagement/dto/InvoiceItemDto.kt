package com.example.BicycleManagement.dto


data class InvoiceItemDto(
    var id : Long,
    var bicycleName : String,
    var quantity : Int,
    var subtotal : Double
)
