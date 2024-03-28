package com.example.BicycleManagement.service.impl

import com.example.BicycleManagement.base.response.MessageResponse
import com.example.BicycleManagement.base.response.ObjectResponse
import com.example.BicycleManagement.base.response.PageResponse
import com.example.BicycleManagement.dto.CustomerDto
import com.example.BicycleManagement.dto.mapper.CustomerMapper
import com.example.BicycleManagement.exception.NotFoundException
import com.example.BicycleManagement.model.Customer
import com.example.BicycleManagement.model.Role
import com.example.BicycleManagement.repository.CustomerRepository
import com.example.BicycleManagement.repository.RoleRepository
import com.example.BicycleManagement.service.CustomerService
import jakarta.persistence.criteria.Predicate
import org.springframework.data.domain.PageRequest
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class CustomerServiceImpl(
    private val customerRepository: CustomerRepository,
    private val roleRepository: RoleRepository,
    private val customerMapper: CustomerMapper,
    private val passwordEncoder: PasswordEncoder
) : CustomerService {
    override fun index(name: String?, size: Int, page: Int): PageResponse<CustomerDto?> {
        val customers = customerRepository.findAll({root , query , builder->
            val predicates = ArrayList<Predicate>()
            name?.let { predicates.add(builder.like(builder.lower(root.get("name")),"${name.lowercase()}$")) }
            query.orderBy(builder.desc(root.get<Long>("id")))
            builder.and(*predicates.toTypedArray())
        } , PageRequest.of(page , size))
        return PageResponse(customers.totalElements , customers.content.map { customerMapper.toDto(it) })
    }

    override fun show(id: Long): ObjectResponse<CustomerDto?> {
        val customer : Customer = customerRepository.findById(id)!!.orElseThrow { NotFoundException("Customer not found with id $id") }
        return ObjectResponse(customerMapper.toDto(customer))
    }

    override fun create(newCustomer: Customer, roleId: Int): MessageResponse {
        val role : Role = roleRepository.findById(roleId)!!.orElseThrow { NotFoundException("role not found with id $roleId") }
        newCustomer.role = role
        newCustomer.password = passwordEncoder.encode(newCustomer.password)
        customerRepository.save(newCustomer).let { return MessageResponse() }
    }

    override fun deleteById(id: Long): MessageResponse {
        TODO("Not yet implemented")
    }

    override fun updateById(id: Long, updateCustomer: Customer): MessageResponse {
        TODO("Not yet implemented")
    }
}