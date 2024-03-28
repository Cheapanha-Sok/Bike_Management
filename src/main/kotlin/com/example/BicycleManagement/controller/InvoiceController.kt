package com.example.BicycleManagement.controller

import com.example.BicycleManagement.base.response.MessageResponse
import com.example.BicycleManagement.base.response.ObjectResponse
import com.example.BicycleManagement.base.response.PageResponse
import com.example.BicycleManagement.dto.InvoiceDto
import com.example.BicycleManagement.model.Invoice
import com.example.BicycleManagement.service.InvoiceService
import com.example.BicycleManagement.util.constants.Constant
import org.springframework.web.bind.annotation.*
import java.util.Date


@RestController
@RequestMapping("${Constant.MAIN_URL}invoices")
class InvoiceController(private val invoiceService: InvoiceService) {
    @GetMapping
    fun index(
        @RequestParam(name = "invoice_date", required = false) name :Date?,
        @RequestParam(name = "size" , defaultValue = "10") size :Int,
        @RequestParam(name = "page" , defaultValue = "0") page : Int
    ) : PageResponse<InvoiceDto?> {
        return invoiceService.index(name , size ,page)
    }
    @GetMapping("/{id}")
    fun show(@PathVariable("id") id :Long) : ObjectResponse<Invoice?> {
        return invoiceService.show(id)
    }
    @DeleteMapping("/delete/{id}")
    fun deleteById(@PathVariable("id") id :Long) : MessageResponse {
        return invoiceService.deleteById(id)
    }
    @PutMapping("/put/{id}")
    fun updateById(@PathVariable("id") id :Long, @RequestBody updatedInvoice : Invoice): MessageResponse {
        return invoiceService.updateById(id , updatedInvoice)
    }
}