package com.example.BicycleManagement.dto.mapper

import com.example.BicycleManagement.dto.InvoiceItemDto
import com.example.BicycleManagement.model.InvoiceItem
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring")
interface InvoiceItemMapper {
    @Mapping(target = "subtotal", source = "subTotalPrice")
    @Mapping(target = "bicycleName", source = "bicycle.name") // Map quantity field
    fun toDto(invoiceItem: InvoiceItem): InvoiceItemDto
}
