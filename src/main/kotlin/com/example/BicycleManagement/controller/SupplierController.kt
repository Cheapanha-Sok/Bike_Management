package com.example.BicycleManagement.controller

import com.example.BicycleManagement.base.response.MessageResponse
import com.example.BicycleManagement.base.response.ObjectResponse
import com.example.BicycleManagement.model.Supplier
import com.example.BicycleManagement.service.SupplierService
import com.example.BicycleManagement.util.constants.Constant
import org.springframework.web.bind.annotation.*
@RestController
@RequestMapping("${Constant.MAIN_URL}supplier")
class SupplierController(private val supplierService: SupplierService) {
    @GetMapping
    fun index() : ObjectResponse<List<Supplier>> {
        return supplierService.index()
    }
    @GetMapping("/{id}")
    fun show(@PathVariable("id") id :Long) : ObjectResponse<Supplier>{
        return supplierService.show(id)
    }
    @PostMapping("/create")
    fun create(@RequestBody newCategory : Supplier) :MessageResponse{
        return supplierService.create(newCategory)
    }
    @DeleteMapping("/delete/{id}")
    fun deleteById(@PathVariable("id") id :Long) : MessageResponse{
        return supplierService.deleteById(id)
    }
    @PutMapping("/put/{id}")
    fun updateById(@PathVariable("id") id :Long, @RequestBody updatedCategory : Supplier) : MessageResponse{
        return supplierService.updateById(id , updatedCategory)
    }
}