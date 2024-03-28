package com.example.BicycleManagement.service.impl

import com.example.BicycleManagement.base.response.MessageResponse
import com.example.BicycleManagement.base.response.ObjectResponse
import com.example.BicycleManagement.base.response.PageResponse
import com.example.BicycleManagement.dto.BicycleDto
import com.example.BicycleManagement.dto.ImportBike
import com.example.BicycleManagement.dto.mapper.BicycleMapper
import com.example.BicycleManagement.exception.NotFoundException
import com.example.BicycleManagement.model.Bicycle
import com.example.BicycleManagement.model.Import
import com.example.BicycleManagement.repository.BicycleRepository
import com.example.BicycleManagement.service.BicycleService
import jakarta.persistence.criteria.Predicate
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import java.util.*
import kotlin.collections.ArrayList

@Service
class BicycleServiceImpl(
    private val bicycleRepository: BicycleRepository,
    private val bicycleMapper: BicycleMapper,
) : BicycleService {
    override fun index(bicycleName: String?, page: Int, size: Int): PageResponse<BicycleDto?> {
        val bicycle = bicycleRepository.findAll({ root, query, builder ->
            val predicates = ArrayList<Predicate>()
            bicycleName?.let {
                predicates.add(builder.like(builder.upper(root.get("name")), "%${it.lowercase(Locale.getDefault())}%"))
            }

            query.orderBy(builder.desc(root.get<Long>("sellPrice")))
            builder.and(*predicates.toTypedArray())
        }, PageRequest.of(page, size))
        return PageResponse(bicycle.totalElements, bicycle.content.map { bicycleMapper.toDto(it) })
    }

    override fun show(id: Long): ObjectResponse<BicycleDto> {
        val bicycle = findById(id)
        return ObjectResponse(bicycleMapper.toDto(bicycle))
    }

//    override fun create(newBicycle: Import, category: Category, sellPrice: Double) {
//        TODO("Not yet implemented")
//    }

        override fun create(import:Import) {
        val bicycles = Bicycle().apply {
            this.quantity = import.quantity
            this.name = import.bikeName
            this.sellPrice = import.sellPrice
            this.manufactureDate = import.manuFactureDate
            this.category = import.category
            this.import = import
        }
        bicycleRepository.save(bicycles)
    }
    override fun deleteById(id: Long): MessageResponse {
        val bicycle: Bicycle = findById(id)
        bicycleRepository.delete(bicycle).let { return MessageResponse() }
    }

    private fun findById(id:Long) : Bicycle{
        return bicycleRepository.findById(id)!!.orElseThrow { NotFoundException("Bicycle not found with id $id") }
    }

    override fun updateById(id: Long, updateBicycle: BicycleDto): MessageResponse {
        val existingBicycle =
            bicycleRepository.findById(id)?.orElseThrow { NotFoundException("Bicycle not found with id: $id") }
        updateBicycle.name?.let { existingBicycle?.name = updateBicycle.name }
        updateBicycle.status?.let { existingBicycle?.status = updateBicycle.status }
        updateBicycle.quantity?.let { existingBicycle?.quantity = updateBicycle.quantity }
        updateBicycle.category?.let { existingBicycle?.category?.name = updateBicycle.name }
        updateBicycle.price?.let { existingBicycle?.sellPrice = updateBicycle.price }
        updateBicycle.manufactureDate?.let { existingBicycle?.manufactureDate = updateBicycle.manufactureDate }
        bicycleRepository.save(existingBicycle).let { return MessageResponse() }
    }

    override fun findByName(name: String): ObjectResponse<List<BicycleDto>> {
        bicycleRepository.findByName(name).let { return ObjectResponse(it.map { bicycleMapper.toDto(it) }) }
    }
}