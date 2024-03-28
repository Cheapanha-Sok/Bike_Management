package com.example.BicycleManagement.service

import com.example.BicycleManagement.base.response.MessageResponse
import com.example.BicycleManagement.base.response.ObjectResponse
import com.example.BicycleManagement.base.response.PageResponse
import com.example.BicycleManagement.dto.SellDto
import com.example.BicycleManagement.model.Bicycle
import com.example.BicycleManagement.model.Invoice
import com.example.BicycleManagement.model.InvoiceItem

interface InvoiceItemService {
    fun index(name :String? , size : Int , page : Int) : PageResponse<InvoiceItem?>
    fun show(id :Long) : ObjectResponse<InvoiceItem?>
    fun create(invoice  : Invoice , bike : Bicycle ,subTotalPrice : Double , quantity : Int)
    fun deleteById(id :Long) : MessageResponse
    fun updateById(id:Long , updatedInvoice: InvoiceItem) : MessageResponse
}