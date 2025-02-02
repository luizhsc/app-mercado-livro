package com.mercadolivro.controller.request

import com.mercadolivro.validation.EmailAvailable
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty

data class PostCustomerRequest(

    @field:NotEmpty(message = "Nome deve ser informado.")
    var name: String,

    @field:Email(message = "E-mail deve ser válido.")
    @EmailAvailable
    var email: String,

    @field:NotEmpty(message = "Senha deve ser informado.")
    var password: String
)