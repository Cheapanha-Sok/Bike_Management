package com.example.BicycleManagement.service

import com.example.BicycleManagement.base.response.MessageResponse
import com.example.BicycleManagement.base.response.ObjectResponse
import com.example.BicycleManagement.model.Category

interface CategoryService {
    fun index() :ObjectResponse<List<Category>>
    fun show(id :Long) : ObjectResponse<Category>
    fun create(newCategory: Category): MessageResponse
    fun deleteById(id :Long) : MessageResponse
    fun updateById(id:Long , updateCategory: Category) : MessageResponse
}