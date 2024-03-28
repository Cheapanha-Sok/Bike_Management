package com.example.BicycleManagement.controller

import com.example.BicycleManagement.base.response.MessageResponse
import com.example.BicycleManagement.base.response.ObjectResponse
import com.example.BicycleManagement.model.Role
import com.example.BicycleManagement.service.RoleService
import com.example.BicycleManagement.util.constants.Constant
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("${Constant.MAIN_URL}role")
class RoleController(private val roleService:RoleService) {
    @GetMapping
    fun index(): ObjectResponse<List<Role>> {
        return roleService.index()
    }

    @PostMapping("/create")
    fun save(@RequestBody newRole: Role): MessageResponse {
        return roleService.save(newRole)
    }

    @GetMapping("{id}")
    fun show(@PathVariable("id") id: Long): ObjectResponse<Role> {
        return roleService.show(id)
    }

    @DeleteMapping("/delete/{id}")
    fun deleteById(@PathVariable("id") id: Long): MessageResponse {
        return roleService.deleteById(id)
    }
    @PutMapping("put/{id}")
    fun updateById(@RequestBody updatedRole : Role , @PathVariable("id") id:Long) : MessageResponse{
        return roleService.updateById(updatedRole , id)
    }


}