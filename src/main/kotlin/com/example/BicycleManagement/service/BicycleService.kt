package com.example.BicycleManagement.service

import com.example.BicycleManagement.base.response.MessageResponse
import com.example.BicycleManagement.base.response.ObjectResponse
import com.example.BicycleManagement.base.response.PageResponse
import com.example.BicycleManagement.dto.BicycleDto
import com.example.BicycleManagement.dto.SellDto
import com.example.BicycleManagement.model.Bicycle
import com.example.BicycleManagement.model.Category
import org.springframework.data.domain.Page

interface BicycleService {
    fun index(bicycleName :String? , page :Int , size :Int) : PageResponse<BicycleDto?>
    fun show(id :Long) :ObjectResponse<BicycleDto>
    fun create(supplierId :Long , newBicycle : List<Bicycle>) : MessageResponse
    fun deleteById(id :Long) : MessageResponse
    fun sell(sellBike: List<SellDto>) : MessageResponse
    fun updateById(id:Long , updateBicycle : BicycleDto) : MessageResponse
    fun findByName(name :String) : ObjectResponse<List<BicycleDto>>
}