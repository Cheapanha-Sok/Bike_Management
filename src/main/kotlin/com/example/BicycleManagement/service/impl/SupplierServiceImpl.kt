package com.example.BicycleManagement.service.impl

import com.example.BicycleManagement.base.response.MessageResponse
import com.example.BicycleManagement.base.response.ObjectResponse
import com.example.BicycleManagement.exception.NotFoundException
import com.example.BicycleManagement.model.Supplier
import com.example.BicycleManagement.repository.SupplierRepository
import com.example.BicycleManagement.service.SupplierService
import org.springframework.stereotype.Service

@Service
class SupplierServiceImpl (
    private val supplierRepository: SupplierRepository
) : SupplierService {
    override fun index() : ObjectResponse<List<Supplier>> {
        supplierRepository.findAll().let { return ObjectResponse(it) }
    }

    override fun show(id: Long): ObjectResponse<Supplier> {
        val supplier : Supplier = supplierRepository.findById(id)!!.orElseThrow { NotFoundException("Supplier not found with id $id") }
        return ObjectResponse(supplier)
    }

    override fun create(newSupplier: Supplier) : MessageResponse {
        supplierRepository.save(newSupplier).let { return MessageResponse() }
    }

    override fun deleteById(id: Long) : MessageResponse {
        val supplier :Supplier = supplierRepository.findById(id)!!.orElseThrow {
            NotFoundException("Supplier with not found with id $id")
        }
        supplierRepository.delete(supplier).let { return MessageResponse() }
    }

    override fun updateById(id: Long, updateSupplier: Supplier):MessageResponse {
        val supplier : Supplier = supplierRepository.findById(id)!!.orElseThrow { NotFoundException("Supplier with id $id not found") }
        updateSupplier.name?.let { supplier.name = updateSupplier.name }
        updateSupplier.phoneNumber?.let { supplier.phoneNumber = updateSupplier.phoneNumber }
        supplierRepository.save(supplier).let { return MessageResponse() }
    }
}