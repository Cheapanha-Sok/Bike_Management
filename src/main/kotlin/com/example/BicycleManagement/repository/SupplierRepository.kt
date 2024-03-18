package com.example.BicycleManagement.repository

import com.example.BicycleManagement.model.Supplier
import org.springframework.data.jpa.repository.JpaRepository

interface SupplierRepository : JpaRepository<Supplier , Long>