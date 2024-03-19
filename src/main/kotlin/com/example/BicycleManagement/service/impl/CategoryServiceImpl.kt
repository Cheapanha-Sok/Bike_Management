package com.example.BicycleManagement.service.impl

import com.example.BicycleManagement.base.response.MessageResponse
import com.example.BicycleManagement.base.response.ObjectResponse
import com.example.BicycleManagement.exception.NotFoundException
import com.example.BicycleManagement.model.Category
import com.example.BicycleManagement.repository.CategoryRepository
import com.example.BicycleManagement.service.CategoryService
import org.springframework.stereotype.Service

@Service
class CategoryServiceImpl (private val categoryRepository: CategoryRepository) : CategoryService {
    override fun index()  : ObjectResponse<List<Category>>{
        categoryRepository.findAll().let { return ObjectResponse(it) }
    }
    override fun show(id: Long): ObjectResponse<Category> {
        val category : Category = categoryRepository.findById(id)!!.orElseThrow { NotFoundException("Category not found with id $id") }
        return ObjectResponse(category)
    }

    override fun create(newCategory: Category) : MessageResponse{
        categoryRepository.save(newCategory).let { return MessageResponse() }
    }

    override fun deleteById(id: Long) : MessageResponse {
        val category : Category = categoryRepository.findById(id)!!.orElseThrow { NotFoundException("Category not found with id $id") }
        categoryRepository.delete(category).let { return MessageResponse() }
    }

    override fun updateById(id: Long, updateCategory: Category) : MessageResponse {
        val category : Category = categoryRepository.findById(id)!!.orElseThrow { NotFoundException("Category not found with id $id") }
        updateCategory.name?.let { category.name = updateCategory.name }
        updateCategory.code?.let { category.code = updateCategory.code }
        categoryRepository.save(category).let { return MessageResponse() }
    }
}