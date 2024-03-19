package com.example.BicycleManagement.dto.mapper

import com.example.BicycleManagement.dto.BicycleDto
import com.example.BicycleManagement.model.Bicycle
import org.springframework.stereotype.Service
import java.time.LocalDate

import java.util.function.Function
@Service
class BicycleMapper : Function<Bicycle , BicycleDto> {

    override fun apply(bicycle: Bicycle): BicycleDto {
        return BicycleDto(
            id = bicycle.id ?: 0,
            name = bicycle.name ?: "Unknown",
            category = bicycle.category?.name ?: "Uncategorized",
            manufactureDate = bicycle.manufactureDate!!,
            status = bicycle.status!!,
            quantity = bicycle.quantity!!,
            price = bicycle.sellPrice ?: bicycle.unitPrice
        )
    }
}