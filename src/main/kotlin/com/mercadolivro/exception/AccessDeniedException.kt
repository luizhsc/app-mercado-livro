package com.mercadolivro.exception

class AccessDeniedException(val errorCode: String, override val message: String) : Exception() {
}