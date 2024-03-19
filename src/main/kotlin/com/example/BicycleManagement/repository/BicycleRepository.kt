package com.example.BicycleManagement.repository

import com.example.BicycleManagement.base.repository.BaseRepository
import com.example.BicycleManagement.model.Bicycle


interface BicycleRepository : BaseRepository<Bicycle, Long> {
    fun findByName(name :String) : List<Bicycle>
}