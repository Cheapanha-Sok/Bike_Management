package com.example.BicycleManagement.service

import com.example.BicycleManagement.base.response.MessageResponse
import com.example.BicycleManagement.base.response.ResponseMessage
import com.example.BicycleManagement.dto.SellDto

interface SellService {
    fun sellBike(customerId : Long, sellBike : List<SellDto>) : ResponseMessage
}