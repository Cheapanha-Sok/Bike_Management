package com.example.BicycleManagement.service.impl

import com.example.BicycleManagement.base.response.MessageResponse
import com.example.BicycleManagement.base.response.ObjectResponse
import com.example.BicycleManagement.base.response.PageResponse
import com.example.BicycleManagement.dto.InvoiceDto
import com.example.BicycleManagement.dto.SellDto
import com.example.BicycleManagement.dto.mapper.InvoiceMapper
import com.example.BicycleManagement.exception.NotFoundException
import com.example.BicycleManagement.model.Bicycle
import com.example.BicycleManagement.model.Customer
import com.example.BicycleManagement.model.Invoice
import com.example.BicycleManagement.repository.BicycleRepository
import com.example.BicycleManagement.repository.InvoiceRepository
import com.example.BicycleManagement.service.InvoiceItemService
import com.example.BicycleManagement.service.InvoiceService
import jakarta.persistence.criteria.Predicate
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import java.time.Instant
import java.util.*
import kotlin.collections.ArrayList

@Service

class InvoiceServiceImpl(
    private val invoiceRepository: InvoiceRepository,
    private val invoiceItemService: InvoiceItemService,
    private val bicycleRepository: BicycleRepository,
    private val invoiceMapper: InvoiceMapper
) : InvoiceService {

    override fun index(date: Date?, size: Int, page: Int): PageResponse<InvoiceDto?> {
        val invoices = invoiceRepository.findAll ({ root, query, builder ->
            val predicates = ArrayList<Predicate>()
            date?.let { builder.like(root.get("invoiceDate"), "&${date}&") }
            query.orderBy(builder.desc(root.get<Date>("invoiceDate")))
            builder.and(*predicates.toTypedArray())
        }, PageRequest.of(page, size))
        return PageResponse(invoices.totalElements , invoices.content.map { invoiceMapper.toDto(it) })
    }

    override fun show(id: Long): ObjectResponse<Invoice?> {
        // Implement show logic here
        TODO("Not yet implemented")
    }

    private fun validateBike(id :Long) : Bicycle{
        val bikeItem = bicycleRepository.findById(id)
            ?.orElseThrow { NotFoundException("Bike not found with id $id") }
        return bikeItem!!
    }

    override fun create(totalPrice: Double, customer: Customer, bike: List<SellDto>) {
        val invoice = Invoice().apply {
            invoiceDate = Date.from(Instant.now())
            this.customer = customer
            this.totalPrice = totalPrice
        }
        val savedInvoice = invoiceRepository.save(invoice)

        bike.map {
            val bikeItem = validateBike(it.bicycleId)
            val subtotal: Double = bikeItem.sellPrice!!.times(it.quantity)
            invoiceItemService.create(savedInvoice, bikeItem, subtotal , it.quantity)
        }
    }

    override fun deleteById(id: Long): MessageResponse {
        // Implement delete logic here
        TODO("Not yet implemented")
    }

    override fun updateById(id: Long, updatedInvoice: Invoice): MessageResponse {
        // Implement update logic here
        TODO("Not yet implemented")
    }
}
