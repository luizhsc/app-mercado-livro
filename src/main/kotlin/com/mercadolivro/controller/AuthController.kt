package com.mercadolivro.controller

import com.mercadolivro.controller.request.AuthenticatRequest
import com.mercadolivro.controller.response.AuthenticateResponse
import com.mercadolivro.service.AuthenticationService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthController(
    private val authenticationService: AuthenticationService
) {

    @PostMapping("/auth/user")
    fun authenticate(@RequestBody request: AuthenticatRequest): AuthenticateResponse =
        authenticationService.authenticate(request)

}