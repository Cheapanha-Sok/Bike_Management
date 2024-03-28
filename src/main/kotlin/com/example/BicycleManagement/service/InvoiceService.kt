package com.example.BicycleManagement.service

import com.example.BicycleManagement.base.response.MessageResponse
import com.example.BicycleManagement.base.response.ObjectResponse
import com.example.BicycleManagement.base.response.PageResponse
import com.example.BicycleManagement.dto.InvoiceDto
import com.example.BicycleManagement.dto.SellDto
import com.example.BicycleManagement.model.Customer
import com.example.BicycleManagement.model.Invoice
import java.util.Date

interface InvoiceService {
    fun index(date :Date? , size : Int , page : Int) : PageResponse<InvoiceDto?>
    fun show(id :Long) : ObjectResponse<Invoice?>
    fun create(totalPrice : Double , customer : Customer , bike : List<SellDto>)
    fun deleteById(id :Long) : MessageResponse
    fun updateById(id:Long , updatedInvoice: Invoice) : MessageResponse
}