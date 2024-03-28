package com.example.BicycleManagement.service

import com.example.BicycleManagement.base.response.MessageResponse
import com.example.BicycleManagement.base.response.ObjectResponse
import com.example.BicycleManagement.model.Role

interface RoleService {
    fun index() : ObjectResponse<List<Role>>
    fun show(id : Long): ObjectResponse<Role>
    fun deleteById(id:Long) : MessageResponse
    fun save(newRole : Role) : MessageResponse
    fun updateById(updatedRole : Role, id:Long) : MessageResponse
}