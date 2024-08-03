package com.mercadolivro.controller

import com.mercadolivro.controller.request.AuthenticatRequest
import com.mercadolivro.controller.request.RefreshTokenRequest
import com.mercadolivro.controller.response.AuthenticationResponse
import com.mercadolivro.controller.response.TokenResponse
import com.mercadolivro.enums.Errors
import com.mercadolivro.exception.AuthenticationException
import com.mercadolivro.extension.toResponse
import com.mercadolivro.service.AuthenticationService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authenticationService: AuthenticationService
) {

    @PostMapping("/user")
    fun authenticate(@RequestBody request: AuthenticatRequest): AuthenticationResponse =
        authenticationService.authentication(request)


    @PostMapping("/refresh")
    fun refresh(@RequestBody request: RefreshTokenRequest): TokenResponse =
        authenticationService.refreshAccessToken(request.token)?.toResponse()
            ?: throw AuthenticationException(Errors.ML997.code, Errors.ML997.message)


}
