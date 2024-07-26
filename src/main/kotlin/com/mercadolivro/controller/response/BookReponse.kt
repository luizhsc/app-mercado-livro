package com.mercadolivro.controller.response

import com.mercadolivro.model.CustomerModel
import java.math.BigDecimal

data class BookReponse(
    var id: Int? = null,
    var name: String,
    var price: BigDecimal,
    var customer: CustomerModel?
)