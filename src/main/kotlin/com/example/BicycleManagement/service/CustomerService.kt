package com.example.BicycleManagement.service

import com.example.BicycleManagement.base.response.MessageResponse
import com.example.BicycleManagement.base.response.ObjectResponse
import com.example.BicycleManagement.base.response.PageResponse
import com.example.BicycleManagement.dto.CustomerDto
import com.example.BicycleManagement.model.Customer

interface CustomerService {
    fun index(name :String? , size : Int , page : Int) : PageResponse<CustomerDto?>
    fun show(id :Long) : ObjectResponse<CustomerDto?>
    fun create(newCustomer: Customer , roleId : Int ): MessageResponse
    fun deleteById(id :Long) : MessageResponse
    fun updateById(id:Long , updateCustomer: Customer) : MessageResponse
}