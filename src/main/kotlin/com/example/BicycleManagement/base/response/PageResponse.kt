package com.example.BicycleManagement.base.response

import org.springframework.stereotype.Component

@Component
class PageResponse<T>(
    var total: Long? = null,
    var results: List<T>? = null,
    var response: ResponseMessage? = ResponseMessage().response()
) {

}