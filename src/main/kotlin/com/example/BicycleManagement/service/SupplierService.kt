package com.example.BicycleManagement.service

import com.example.BicycleManagement.base.response.MessageResponse
import com.example.BicycleManagement.base.response.ObjectResponse
import com.example.BicycleManagement.model.Supplier

interface SupplierService {
    fun index() : ObjectResponse<List<Supplier>>
    fun show(id :Long) : ObjectResponse<Supplier>
    fun create(newSupplier: Supplier) : MessageResponse
    fun deleteById(id :Long) : MessageResponse
    fun updateById(id:Long , updateSupplier: Supplier) : MessageResponse
}