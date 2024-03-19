package com.example.BicycleManagement.repository

import CrudRepository
import com.example.BicycleManagement.model.Bicycle
import org.springframework.data.jpa.repository.JpaRepository


interface BicycleRepository : CrudRepository<Bicycle , Long> {
}