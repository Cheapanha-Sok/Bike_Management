package com.example.BicycleManagement.dto.mapper.impl

import com.example.BicycleManagement.dto.InvoiceDto
import com.example.BicycleManagement.dto.mapper.InvoiceItemMapper
import com.example.BicycleManagement.dto.mapper.InvoiceMapper
import com.example.BicycleManagement.model.Invoice
import org.springframework.stereotype.Component


@Component
class InvoiceMapperImpl (private val invoiceItemMapper: InvoiceItemMapper) : InvoiceMapper {
    override fun toDto(invoice: Invoice): InvoiceDto {
        return InvoiceDto(
            id = invoice.id!!,
            customerName = invoice.customer!!.name!!,
            customerEmail = invoice.customer!!.email!!,
            invoiceDate = invoice.invoiceDate!!,
            totalPrice = invoice.totalPrice!!,
            invoiceItems = invoice.invoiceItems!!.map { invoiceItemMapper.toDto(it) }
        )
    }
}