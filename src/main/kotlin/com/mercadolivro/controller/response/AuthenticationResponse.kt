package com.mercadolivro.controller.response

data class AuthenticationResponse (
    val accessToken: String,
    val refreshToken: String
)

