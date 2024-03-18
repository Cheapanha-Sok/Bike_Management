package com.example.BicycleManagement.repository

import com.example.BicycleManagement.model.Category
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface CategoryRepository: JpaRepository<Category , Long> {
    fun findByName(name:String) : Optional<Category>
}