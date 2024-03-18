package com.example.BicycleManagement.controller

import com.example.BicycleManagement.base.response.MessageResponse
import com.example.BicycleManagement.base.response.ObjectResponse
import com.example.BicycleManagement.model.Category
import com.example.BicycleManagement.service.CategoryService
import com.example.BicycleManagement.util.constants.Constant
import org.springframework.web.bind.annotation.*
@RestController
@RequestMapping("${Constant.MAIN_URL}category")
class CategoryController (private val categoryService: CategoryService) {
    @GetMapping
    fun index() : ObjectResponse<List<Category>>{
        return categoryService.index()
    }
    @GetMapping("/{id}")
    fun show(@PathVariable("id") id :Long) : ObjectResponse<Category>{
        return categoryService.show(id)
    }
    @PostMapping("/create")
    fun create(@RequestBody newCategory : Category): MessageResponse {
        return categoryService.create(newCategory)
    }
    @DeleteMapping("/delete/{id}")
    fun deleteById(@PathVariable("id") id :Long) : MessageResponse{
        return categoryService.deleteById(id)
    }
    @PutMapping("/put/{id}")
    fun updateById(@PathVariable("id") id :Long, @RequestBody updatedCategory : Category): MessageResponse{
        return categoryService.updateById(id , updatedCategory)
    }
}