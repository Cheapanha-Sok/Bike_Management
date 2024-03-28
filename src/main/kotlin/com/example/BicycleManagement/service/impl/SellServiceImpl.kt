package com.example.BicycleManagement.service.impl

import com.example.BicycleManagement.base.response.ResponseMessage
import com.example.BicycleManagement.dto.SellDto
import com.example.BicycleManagement.exception.NotFoundException
import com.example.BicycleManagement.model.Bicycle
import com.example.BicycleManagement.model.Customer
import com.example.BicycleManagement.repository.BicycleRepository
import com.example.BicycleManagement.repository.CustomerRepository
import com.example.BicycleManagement.service.InvoiceService
import com.example.BicycleManagement.service.SellService
import org.springframework.stereotype.Service

@Service
class SellServiceImpl(
    private val customerRepository: CustomerRepository,
    private val bicycleRepository: BicycleRepository,
    private val invoiceService: InvoiceService
) : SellService {

    private fun validateBike(bikeId: Long): Bicycle {
        val bike =
            bicycleRepository.findById(bikeId)!!.orElseThrow { NotFoundException("Bike not found with id $bikeId") }
        return bike
    }
    private fun checkQuantityMustGreaterThanZero(sellBike: List<SellDto>):Boolean{
        return sellBike.all { it.quantity > 0 }
    }
    private fun checkBikeIsValid(sellBike: List<SellDto>): Boolean {
        var isValid : Boolean = true
        for (item in sellBike) {
            val bike = validateBike(item.bicycleId)
            if (bike.status == false) {
                isValid = false
                break
            }
        }
        return isValid
    }
    private fun checkValidQuantity(sellBike: List<SellDto>) : Boolean{
        var isValid : Boolean = true
        for (item in sellBike){
            val bike = validateBike(item.bicycleId)
            if (item.quantity > bike.quantity!!){
                isValid = false
                break
            }
        }
        return isValid
    }
    override fun sellBike(customerId: Long, sellBike: List<SellDto>) : ResponseMessage {
        if (!checkQuantityMustGreaterThanZero(sellBike)) {
            throw RuntimeException("The quantity must be greater than zero for all bicycles.")
        }
        if (!checkBikeIsValid(sellBike)) {
            throw RuntimeException("One or more bicycles in the list are not available for sale.")
        }
        if (!checkValidQuantity(sellBike)) {
            throw RuntimeException("One or more bicycles in the list have insufficient quantity for sale.")
        }
        val customer: Customer = customerRepository.findById(customerId)!!
            .orElseThrow { NotFoundException("Customer not found with id $customerId") }
        var totalPrice : Double= 0.0
        var subtotal : Double = 0.0

        sellBike.map {
            val bike = validateBike(it.bicycleId)
            subtotal = bike.sellPrice!! * it.quantity
            totalPrice += subtotal
            val remainingQuantity = bike.quantity!! - it.quantity
            bike.quantity = remainingQuantity
            bike.status = remainingQuantity > 0
            bicycleRepository.save(bike)
        }
        invoiceService.create(totalPrice ,customer, bike = sellBike)
        return ResponseMessage()
    }
}