package com.example.BicycleManagement.dto.mapper

import com.example.BicycleManagement.dto.InvoiceDto
import com.example.BicycleManagement.model.Invoice
import org.mapstruct.Mapper

//@Mapper(componentModel = "spring")
//interface InvoiceMapper {
//    @Mapping(target = "invoiceId" , source = "id")
//    @Mapping(target = "customerName" , source = "customer.name")
//    @Mapping(target = "customerEmail" , source = "customer.email")
//    @Mapping(target = "invoiceItems" , source = "invoiceItems")
//    fun toDto(invoice: Invoice ): InvoiceDto
//}

@Mapper
interface InvoiceMapper {
    fun toDto(invoice: Invoice ): InvoiceDto
}