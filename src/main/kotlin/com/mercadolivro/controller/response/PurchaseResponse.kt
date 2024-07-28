package com.mercadolivro.controller.response

import java.math.BigDecimal
import java.time.LocalDateTime

data class PurchaseResponse(
    val idPurchase: Int? = null,
    val idBooks: List<Int?>,
    val nfe: String? = null,
    val price: BigDecimal,
    val createdAt: LocalDateTime
)