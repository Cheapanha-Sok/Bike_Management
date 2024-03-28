package com.example.BicycleManagement.dto.mapper

import com.example.BicycleManagement.dto.ImportBike
import com.example.BicycleManagement.model.Bicycle
import com.example.BicycleManagement.model.Import
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring")
interface ImportMapper {

    @Mapping(target = "bikeName" , source = "name")
    @Mapping(target = "manuFactureDate" , source = "manufactureDate")
    fun toModel(importBike: ImportBike) : Import
}