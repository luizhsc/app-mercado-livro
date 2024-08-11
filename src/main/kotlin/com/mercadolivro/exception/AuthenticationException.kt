package com.mercadolivro.exception

class AuthenticationException(val errorCode: String, override val message: String) : Exception() {

}