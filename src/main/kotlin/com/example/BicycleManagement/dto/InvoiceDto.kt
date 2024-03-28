package com.example.BicycleManagement.dto

import java.util.Date

data class InvoiceDto(
    var id : Long,
    var invoiceDate : Date,
    var customerName : String,
    var customerEmail : String,
    var totalPrice : Double,
    var invoiceItems : List<InvoiceItemDto>
)

