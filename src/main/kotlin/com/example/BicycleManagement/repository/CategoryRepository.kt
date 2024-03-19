package com.example.BicycleManagement.repository

import CrudRepository
import com.example.BicycleManagement.model.Category
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface CategoryRepository: CrudRepository<Category , Long> {
    fun findByName(name :String) : Optional<Category>
}