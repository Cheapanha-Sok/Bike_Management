package com.example.BicycleManagement.repository

import com.example.BicycleManagement.base.repository.BaseRepository
import com.example.BicycleManagement.model.Customer
import com.example.BicycleManagement.model.RefreshToken
import java.util.Optional

interface RefreshTokenRepository : BaseRepository<RefreshToken , Long> {
    fun findByToken(token :String) : Optional<RefreshToken>
    fun findByCustomer(customer: Customer) : Optional<RefreshToken>

}