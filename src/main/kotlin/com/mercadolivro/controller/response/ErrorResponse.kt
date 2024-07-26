package com.mercadolivro.controller.response

data class ErrorResponse(
    var code: String,
    var message: String,
    var errors: List<FieldErrorResponse>?
)

