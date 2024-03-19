package com.example.BicycleManagement.service.impl

import com.example.BicycleManagement.base.response.MessageResponse
import com.example.BicycleManagement.base.response.ObjectResponse
import com.example.BicycleManagement.base.response.PageResponse
import com.example.BicycleManagement.dto.BicycleDto
import com.example.BicycleManagement.dto.SellDto
import com.example.BicycleManagement.dto.mapper.BicycleMapper
import com.example.BicycleManagement.exception.NotFoundException
import com.example.BicycleManagement.model.Bicycle
import com.example.BicycleManagement.model.Supplier
import com.example.BicycleManagement.repository.BicycleRepository
import com.example.BicycleManagement.repository.CategoryRepository
import com.example.BicycleManagement.repository.SupplierRepository
import com.example.BicycleManagement.service.BicycleService
import jakarta.persistence.criteria.Predicate
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import java.util.*
import kotlin.collections.ArrayList

@Service
class BicycleServiceImpl(
    private val bicycleRepository: BicycleRepository,
    private val supplierRepository: SupplierRepository,
    private val categoryRepository: CategoryRepository,
    private val bicycleMapper: BicycleMapper
) : BicycleService {
    override fun index(bicycleName: String?, page: Int, size: Int): PageResponse<BicycleDto?> {
        val bicycle = bicycleRepository.findAll({ root, cq, cb ->
            val predicates = ArrayList<Predicate>()
            bicycleName?.let {
                predicates.add(cb.like(cb.upper(root.get("name")), "%${it.lowercase(Locale.getDefault())}%"))
            }

            cq.orderBy(cb.desc(root.get<Long>("id")))
            cb.and(*predicates.toTypedArray())
        }, PageRequest.of(page, size))
        return PageResponse(bicycle.totalElements, bicycle.content.map { item -> bicycleMapper.apply(item) })
    }

    override fun show(id: Long): ObjectResponse<BicycleDto> {
        val bicycle = bicycleRepository.findById(id)?.orElseThrow { NotFoundException("Bicycle with id $id not found") }
        return ObjectResponse(bicycleMapper.apply(bicycle!!))
    }

    override fun create(supplierId: Long, newBicycle: List<Bicycle>): MessageResponse {
        val supplier: Supplier = supplierRepository.findById(supplierId)!!
            .orElseThrow { NotFoundException("Supplier not found with id $supplierId") }

        newBicycle.map { bicycle ->
            val category = bicycle.category
            if (category != null) {
                val savedCategory = categoryRepository.findByName(category.name!!)
                bicycle.category = savedCategory.get()
            }
            bicycle.suppliers = listOf(supplier)
        }

        bicycleRepository.saveAll(newBicycle)
        return MessageResponse()
    }

    override fun deleteById(id: Long): MessageResponse {
        val bicycle: Bicycle =
            bicycleRepository.findById(id)!!.orElseThrow { NotFoundException("Bicycle not found with id $id") }
        bicycleRepository.delete(bicycle).let { return MessageResponse() }
    }

    override fun sell(sellBike: List<SellDto>): MessageResponse {
        sellBike.forEach { bike ->
            val existingBike = bicycleRepository.findById(bike.id)!!
            existingBike.isPresent.let {
                val quantityToSell = bike.quantity
                if (quantityToSell > 0) {
                    val existingQuantity = existingBike.get().quantity ?: 0
                    val newQuantity = existingQuantity - quantityToSell
                    existingBike.get().quantity = newQuantity
                    existingBike.get().status = newQuantity > 0
                    bicycleRepository.save(existingBike.get())
                } else
                    throw RuntimeException("Bicycle out of stock")
            }
        }
        return MessageResponse()
    }
    override fun updateById(id: Long, updateBicycle: BicycleDto): MessageResponse {
        val existingBicycle = bicycleRepository.findById(id)?.orElseThrow { NotFoundException("Bicycle not found with id: $id") }
        updateBicycle.name?.let { existingBicycle?.name = updateBicycle.name }
        updateBicycle.status?.let { existingBicycle?.status = updateBicycle.status }
        updateBicycle.quantity?.let { existingBicycle?.quantity = updateBicycle.quantity }
        updateBicycle.category?.let { existingBicycle?.category?.name = updateBicycle.name }
        updateBicycle.price?.let { existingBicycle?.sellPrice = updateBicycle.price }
        updateBicycle.manufactureDate?.let { existingBicycle?.manufactureDate = updateBicycle.manufactureDate }
        bicycleRepository.save(existingBicycle).let { return MessageResponse() }
    }

    override fun findByName(name: String): ObjectResponse<List<BicycleDto>> {
        bicycleRepository.findByName(name).let { return ObjectResponse(it.map { bicycleMapper.apply(it) }) }
    }
}