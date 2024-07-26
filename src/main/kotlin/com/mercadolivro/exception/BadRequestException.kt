package com.mercadolivro.exception

import java.lang.Exception

class BadRequestException(val errorCode: String, override val message: String) : Exception() {

}