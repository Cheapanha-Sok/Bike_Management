package com.example.BicycleManagement.repository

import com.example.BicycleManagement.model.Bicycle
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository

@Repository
interface BicycleRepository : JpaRepository<Bicycle , Long>  , JpaSpecificationExecutor<Bicycle>{
}