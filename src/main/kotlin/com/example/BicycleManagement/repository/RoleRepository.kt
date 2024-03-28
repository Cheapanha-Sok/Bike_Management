package com.example.BicycleManagement.repository

import com.example.BicycleManagement.base.repository.BaseRepository
import com.example.BicycleManagement.model.Role
import java.util.Optional

interface RoleRepository : BaseRepository<Role , Int> {
    fun findByName(name :String) : Optional<Role>
}