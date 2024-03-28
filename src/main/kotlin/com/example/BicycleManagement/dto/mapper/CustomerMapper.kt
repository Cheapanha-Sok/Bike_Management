package com.example.BicycleManagement.dto.mapper

import com.example.BicycleManagement.dto.CustomerDto
import com.example.BicycleManagement.model.Customer
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring")
interface CustomerMapper {
    @Mapping(target = "role" , source = "role.name")
    fun toDto(customer: Customer) :CustomerDto
}