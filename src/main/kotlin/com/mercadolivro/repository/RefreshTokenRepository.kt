package com.mercadolivro.repository

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component

@Component
class RefreshTokenRepository {

    private val tokens: MutableMap<String, UserDetails>
        get() = mutableMapOf<String, UserDetails>()

    fun findUserDetailsByToken(token: String): UserDetails? = tokens[token]

    fun save(token: String, userDetails: UserDetails) {
        tokens[token] = userDetails
    }
}