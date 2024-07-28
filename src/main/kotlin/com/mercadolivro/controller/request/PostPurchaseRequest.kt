package com.mercadolivro.controller.request

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive

data class PostPurchaseRequest(

    @field:NotEmpty
    @field:Positive
    val customerId: Int,

    @field:NotNull
    val booksIds: List<Int>

)