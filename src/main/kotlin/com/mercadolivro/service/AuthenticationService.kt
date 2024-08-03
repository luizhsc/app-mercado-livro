package com.mercadolivro.service

import com.mercadolivro.config.JwtProperties
import com.mercadolivro.controller.request.AuthenticatRequest
import com.mercadolivro.controller.response.AuthenticateResponse
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Service
import java.util.*

@Service
class AuthenticationService(
    private val authManager: AuthenticationManager,
    private val userDetailsService: CustomerUserDetailsService,
    private val tokenService: TokenService,
    private val jwtProperties: JwtProperties
) {
    fun authenticate(request: AuthenticatRequest): AuthenticateResponse {
        authManager.authenticate(UsernamePasswordAuthenticationToken(request.email, request.password))
        val user = userDetailsService.loadUserByUsername(request.email)
        val accessToken = tokenService.generate(
            user,
            expirationDate = Date(System.currentTimeMillis() + jwtProperties.accessTokenExpiration)
        )
        return AuthenticateResponse(accessToken = accessToken)
    }
}
