package com.mercadolivro.exception

class NotFoundException(val errorCode: String, override val message: String) : Exception() {

}