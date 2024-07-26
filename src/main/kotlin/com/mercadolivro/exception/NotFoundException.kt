package com.mercadolivro.exception

import java.lang.Exception

class NotFoundException(val errorCode: String, override val message: String) : Exception() {

}