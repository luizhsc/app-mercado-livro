package com.mercadolivro.enums

enum class Errors(val code: String, val message: String) {
    ML001("ML-001", "Invalid Request"),

    ML101("ML-101", "Book [%s] not exist!"),
    ML102("ML-102", "Book status [%s] can not be deleted"),
    ML103("ML-103", "Book can not be seller"),

    ML201("ML-201", "Customer [%s] not exist!")

}