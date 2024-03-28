package com.example.BicycleManagement.controller

import com.example.BicycleManagement.base.response.MessageResponse
import com.example.BicycleManagement.base.response.ObjectResponse
import com.example.BicycleManagement.base.response.PageResponse
import com.example.BicycleManagement.dto.CustomerDto
import com.example.BicycleManagement.model.Customer
import com.example.BicycleManagement.service.CustomerService
import com.example.BicycleManagement.util.constants.Constant
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("${Constant.MAIN_URL}customer")
class CustomerController (private val customerService: CustomerService) {
    @GetMapping
    fun index(
        @RequestParam(name = "name", required = false) name :String?,
        @RequestParam(name = "size" , defaultValue = "10") size :Int,
        @RequestParam(name = "page" , defaultValue = "0") page : Int
    ) : PageResponse<CustomerDto?> {
        return customerService.index(name , size ,page)
    }
    @GetMapping("/{id}")
    fun show(@PathVariable("id") id :Long) : ObjectResponse<CustomerDto?> {
        return customerService.show(id)
    }
    @PostMapping("/create/{role_id}")
    fun create(@RequestBody newCategory : Customer , @PathVariable("role_id") id : Int): MessageResponse {
        return customerService.create(newCategory , id)
    }
    @DeleteMapping("/delete/{id}")
    fun deleteById(@PathVariable("id") id :Long) : MessageResponse {
        return customerService.deleteById(id)
    }
    @PutMapping("/put/{id}")
    fun updateById(@PathVariable("id") id :Long, @RequestBody updatedCustomer : Customer): MessageResponse {
        return customerService.updateById(id , updatedCustomer)
    }
}