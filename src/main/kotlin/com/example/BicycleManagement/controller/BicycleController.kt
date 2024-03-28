package com.example.BicycleManagement.controller

import com.example.BicycleManagement.dto.BicycleDto
import com.example.BicycleManagement.base.response.MessageResponse
import com.example.BicycleManagement.base.response.ObjectResponse
import com.example.BicycleManagement.base.response.PageResponse
import com.example.BicycleManagement.dto.ImportBike
import com.example.BicycleManagement.dto.SellDto
import com.example.BicycleManagement.model.Bicycle
import com.example.BicycleManagement.service.BicycleService
import com.example.BicycleManagement.service.ImportService
import com.example.BicycleManagement.service.SellService
import com.example.BicycleManagement.util.constants.Constant
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("${Constant.MAIN_URL}bicycle")
class BicycleController(
    private val bicycleService: BicycleService,
    private val importService: ImportService,
    private val sellService: SellService) {
    @GetMapping
    fun index(
        @RequestParam(required = false) bicycleName :String?,
        @RequestParam(defaultValue = "0") page :Int,
        @RequestParam(defaultValue = "10") size :Int
    ) : PageResponse<BicycleDto?> {
        return bicycleService.index(bicycleName , page , size)
    }

    @GetMapping("/{id}")
    fun show(@PathVariable("id") id: Long) : ObjectResponse<BicycleDto> {
        return bicycleService.show(id)
    }
    @GetMapping("name/{name}")
    fun show(@PathVariable("name") name: String) : ObjectResponse<List<BicycleDto>> {
        return bicycleService.findByName(name)
    }

    @PostMapping("/create")
    fun create(
        @RequestBody newBicycle: List<ImportBike>,
    ): MessageResponse {
        return importService.create(newBicycle)
    }
    @PostMapping("/sell/{customer_id}")
    fun sell( @RequestBody  sellBike :List<SellDto> , @PathVariable("customer_id") id : Long) : Any{
        return sellService.sellBike(id , sellBike)
    }

    @DeleteMapping("/delete/{id}")
    fun deleteById(@PathVariable("id") id: Long):MessageResponse {
        return bicycleService.deleteById(id)
    }

    @PutMapping("/put/{id}")
    fun updateById(@PathVariable("id") id: Long, @RequestBody updatedBicycle: BicycleDto) : MessageResponse {
        return bicycleService.updateById(id, updatedBicycle)
    }
}