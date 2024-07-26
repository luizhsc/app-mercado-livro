package com.mercadolivro.controller.request

import com.fasterxml.jackson.annotation.JsonAlias
import jakarta.validation.constraints.NotEmpty
import java.math.BigDecimal

data class PostBookRequest(

    @field:NotEmpty(message = "Nome deve ser informado.")
    var name: String,

    @field:NotEmpty(message = "Preço deve ser informado.")
    var price: BigDecimal,

    @field:NotEmpty
    @JsonAlias("customer_id")
    var customerId: Int
)
