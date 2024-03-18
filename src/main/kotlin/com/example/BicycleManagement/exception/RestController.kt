package com.example.BicycleManagement.exception

import com.example.BicycleManagement.dto.NotFoundDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.time.LocalTime

@RestControllerAdvice
class RestController {
    @ExceptionHandler(NotFoundException::class)
    fun handleNotFoundHandler(ex : NotFoundException) :ResponseEntity<NotFoundDto>{
        val errorMessage = NotFoundDto(
            HttpStatus.NOT_FOUND.value(),
            ex.message,
            localTime = LocalTime.now()
        )
        return ResponseEntity(errorMessage , HttpStatus.NOT_FOUND)
    }
}