package com.example.BicycleManagement.service.impl

import com.example.BicycleManagement.base.response.MessageResponse
import com.example.BicycleManagement.base.response.ObjectResponse
import com.example.BicycleManagement.model.Role
import com.example.BicycleManagement.repository.RoleRepository
import com.example.BicycleManagement.service.RoleService
import org.springframework.stereotype.Service


@Service
class RoleServiceImpl(private val roleRepository: RoleRepository) : RoleService {
    override fun index(): ObjectResponse<List<Role>> {
        return ObjectResponse(roleRepository.findAll())
    }

    override fun show(id: Long): ObjectResponse<Role> {
        TODO("Not yet implemented")
    }

    override fun deleteById(id: Long): MessageResponse {
        TODO("Not yet implemented")
    }

    override fun save(newRole: Role): MessageResponse {
        roleRepository.save(newRole).let { return MessageResponse() }
    }

    override fun updateById(updatedRole: Role, id: Long): MessageResponse {
        TODO("Not yet implemented")
    }
}