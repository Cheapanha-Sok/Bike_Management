package com.example.BicycleManagement.repository

import com.example.BicycleManagement.base.repository.BaseRepository
import com.example.BicycleManagement.model.Category
import java.util.Optional

interface CategoryRepository: BaseRepository<Category, Long> {
    fun findByName(name :String) : Optional<Category>
}