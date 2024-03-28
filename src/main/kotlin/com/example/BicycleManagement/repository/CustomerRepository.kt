package com.example.BicycleManagement.repository

import com.example.BicycleManagement.base.repository.BaseRepository
import com.example.BicycleManagement.model.Customer
import java.util.Optional

interface CustomerRepository : BaseRepository<Customer,Long>{
    fun findByEmail(email : String) : Optional<Customer>
}