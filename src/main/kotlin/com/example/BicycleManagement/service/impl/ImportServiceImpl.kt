package com.example.BicycleManagement.service.impl

import com.example.BicycleManagement.base.response.MessageResponse
import com.example.BicycleManagement.base.response.ObjectResponse
import com.example.BicycleManagement.dto.ImportBike
import com.example.BicycleManagement.dto.mapper.ImportMapper
import com.example.BicycleManagement.exception.NotFoundException
import com.example.BicycleManagement.model.Category
import com.example.BicycleManagement.model.Import
import com.example.BicycleManagement.model.Supplier
import com.example.BicycleManagement.repository.CategoryRepository
import com.example.BicycleManagement.repository.ImportRepository
import com.example.BicycleManagement.repository.SupplierRepository
import com.example.BicycleManagement.service.BicycleService
import com.example.BicycleManagement.service.ImportService
import org.springframework.stereotype.Service
import java.time.Instant
import java.time.LocalDate
import java.util.*


@Service
class ImportServiceImpl(
    private val supplierRepository: SupplierRepository,
    private val importRepository: ImportRepository,
    private val categoryRepository: CategoryRepository,
    private val bicycleService: BicycleService,
    private val importMapper: ImportMapper) : ImportService {
//    override fun index(): ObjectResponse<ImportDto> {
//        TODO("Not yet implemented")
//    }
//
//    override fun show(): ObjectResponse<ImportDto> {
//        TODO("Not yet implemented")
//    }

    override fun create(bicycles: List<ImportBike>): MessageResponse {
        val import = bicycles.map {
            val supplier = findSupplier(it.supplierId)
            val category = findCategory(it.categoryId)
            it.category = category
            it.supplier = supplier
            it.createAt = LocalDate.now()
            it.sellPrice = it.sellPrice
            importMapper.toModel(it)
        }
        importRepository.saveAll(import)!!.map {
            bicycleService.create(it)
        }
        return MessageResponse()
    }

    private fun findSupplier(id :Long) : Supplier {
        return supplierRepository.findById(id)!!
            .orElseThrow { NotFoundException("Supplier not found with id $id") }
    }
    private fun findCategory(id : Long) : Category{
        return categoryRepository.findById(id)!!.orElseThrow { NotFoundException("Category not found with id $id") }
    }
//    override fun create(bicycles: List<ImportBike>) : MessageResponse {
//        var totalQuantity: Int = 0
//        val imports = bicycles.map {
//            val supplier = findSupplier(it.supplierId)
//            it.supplier = supplier
//            totalQuantity += it.quantity
//            it.totalQuantity = totalQuantity
//            importMapper.toImportModel(it)
//        }
//        val savedImport = importRepository.saveAll(imports)
//        bicycles.map {
//            savedImport!!.map { import -> importItemService.create(it, import) }
//        }
//        return MessageResponse()
//    }
}