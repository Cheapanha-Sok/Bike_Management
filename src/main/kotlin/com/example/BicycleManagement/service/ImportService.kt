package com.example.BicycleManagement.service

import com.example.BicycleManagement.base.response.MessageResponse
import com.example.BicycleManagement.base.response.ObjectResponse
import com.example.BicycleManagement.dto.ImportBike

interface ImportService {
//    fun index (): ObjectResponse<ImportDto>
//    fun show() : ObjectResponse<ImportDto>
    fun create(bicycles : List<ImportBike>) : MessageResponse
}