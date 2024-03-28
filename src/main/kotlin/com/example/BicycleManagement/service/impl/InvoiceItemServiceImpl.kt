package com.example.BicycleManagement.service.impl

import com.example.BicycleManagement.base.response.MessageResponse
import com.example.BicycleManagement.base.response.ObjectResponse
import com.example.BicycleManagement.base.response.PageResponse
import com.example.BicycleManagement.dto.SellDto
import com.example.BicycleManagement.model.Bicycle
import com.example.BicycleManagement.model.Invoice
import com.example.BicycleManagement.model.InvoiceItem
import com.example.BicycleManagement.repository.BicycleRepository
import com.example.BicycleManagement.repository.InvoiceItemRepository
import com.example.BicycleManagement.service.InvoiceItemService
import org.springframework.stereotype.Service


@Service
class InvoiceItemServiceImpl(
    private val invoiceItemRepository: InvoiceItemRepository
) : InvoiceItemService {
    override fun index(name: String?, size: Int, page: Int): PageResponse<InvoiceItem?> {
        TODO("Not yet implemented")
    }

    override fun show(id: Long): ObjectResponse<InvoiceItem?> {
        TODO("Not yet implemented")
    }

    override fun create(invoice: Invoice, bike: Bicycle, subTotalPrice: Double, quantity: Int) {
        val invoiceItem = InvoiceItem().apply {
            this.quantity = quantity
            this.subTotalPrice = subTotalPrice
            this.invoices = invoice
            this.bicycle = bike
        }
        invoiceItemRepository.save(invoiceItem)
    }

    override fun deleteById(id: Long): MessageResponse {
        TODO("Not yet implemented")
    }

    override fun updateById(id: Long, updatedInvoice: InvoiceItem): MessageResponse {
        TODO("Not yet implemented")
    }
}