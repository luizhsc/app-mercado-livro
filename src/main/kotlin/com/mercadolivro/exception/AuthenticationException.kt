package com.mercadolivro.exception

import java.lang.Exception

class AuthenticationException(val errorCode: String, override val message: String) : Exception() {

}