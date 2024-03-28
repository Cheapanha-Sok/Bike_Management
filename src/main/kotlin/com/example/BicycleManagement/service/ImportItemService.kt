package com.example.BicycleManagement.service

import com.example.BicycleManagement.dto.ImportBike
import com.example.BicycleManagement.model.Import

interface ImportItemService {
    fun create(bicycles : ImportBike , import : Import)
}