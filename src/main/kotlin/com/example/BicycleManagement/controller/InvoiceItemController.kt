package com.example.BicycleManagement.controller

import com.example.BicycleManagement.base.response.MessageResponse
import com.example.BicycleManagement.base.response.ObjectResponse
import com.example.BicycleManagement.base.response.PageResponse
import com.example.BicycleManagement.model.InvoiceItem
import com.example.BicycleManagement.service.InvoiceItemService
import com.example.BicycleManagement.util.constants.Constant
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("${Constant.MAIN_URL}/invoiceItem")
class InvoiceItemController(private val invoiceItemService: InvoiceItemService) {
    @GetMapping
    fun index(
        @RequestParam(name = "name", required = false) name :String?,
        @RequestParam(name = "size" , defaultValue = "10") size :Int,
        @RequestParam(name = "page" , defaultValue = "0") page : Int
    ) : PageResponse<InvoiceItem?> {
        return invoiceItemService.index(name , size ,page)
    }
    @GetMapping("/{id}")
    fun show(@PathVariable("id") id :Long) : ObjectResponse<InvoiceItem?> {
        return invoiceItemService.show(id)
    }
    @DeleteMapping("/delete/{id}")
    fun deleteById(@PathVariable("id") id :Long) : MessageResponse {
        return invoiceItemService.deleteById(id)
    }
    @PutMapping("/put/{id}")
    fun updateById(@PathVariable("id") id :Long, @RequestBody updatedInvoice : InvoiceItem): MessageResponse {
        return invoiceItemService.updateById(id , updatedInvoice)
    }
}