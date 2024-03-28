package com.example.BicycleManagement.dto.mapper

import com.example.BicycleManagement.dto.BicycleDto
import com.example.BicycleManagement.model.Bicycle
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring")
interface BicycleMapper {
    @Mapping(target = "category" , source = "category.name")
    @Mapping(target = "price" , source = "sellPrice")
    fun toDto(bicycle: Bicycle) : BicycleDto
}