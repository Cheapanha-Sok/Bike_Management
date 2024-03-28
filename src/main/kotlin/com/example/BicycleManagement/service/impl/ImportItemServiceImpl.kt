package com.example.BicycleManagement.service.impl

import com.example.BicycleManagement.dto.ImportBike
import com.example.BicycleManagement.exception.NotFoundException
import com.example.BicycleManagement.model.Category
import com.example.BicycleManagement.model.Import
import com.example.BicycleManagement.repository.CategoryRepository
import com.example.BicycleManagement.repository.ImportRepository
import com.example.BicycleManagement.service.BicycleService
import com.example.BicycleManagement.service.ImportItemService
import org.springframework.stereotype.Service

@Service
abstract class ImportItemServiceImpl(
    private val importRepository: ImportRepository,
    private val bicycleService: BicycleService,
    private val categoryRepository: CategoryRepository,
) : ImportItemService {

    private fun findCategory(id:Long) : Category {
        return categoryRepository.findById(id)!!.orElseThrow { NotFoundException("Category not found with id $id") }
    }

//    override fun create(bicycles: ImportBike , import : Import) {
//        val category = findCategory(bicycles.categoryId)
//        val importItem = Import().apply {
//            this.quantity = bicycles.quantity
//            this.bicycleName = bicycles.name
//            this.manufactureDate = bicycles.manufactureDate
//            this.category = category
//            this.price = bicycles.unitPrice
//            this.import = import
//        }
//        importItemRepository.save(importItem).let { bicycleService.create(it , category , bicycles.sellPrice) }
//    }
}