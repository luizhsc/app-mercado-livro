package com.mercadolivro.exception

class BadRequestException(val errorCode: String, override val message: String) : Exception() {

}