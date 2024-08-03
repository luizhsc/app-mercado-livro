package com.mercadolivro.service

import com.mercadolivro.config.JwtProperties
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.util.*

@Service
class TokenService(
    jwtProperties: JwtProperties
) {
    private val secreatKey = Keys.hmacShaKeyFor(
        jwtProperties.key.toByteArray(),
    )

    fun generate(
        userDetails: UserDetails, expirationDate: Date, additionalClaims: Map<String, Any> = emptyMap()
    ): String =
        Jwts.builder()
            .claims()
            .subject(userDetails.username)
            .issuedAt(Date(System.currentTimeMillis()))
            .expiration(expirationDate)
            .add(additionalClaims)
            .and()
            .signWith(secreatKey)
            .compact()

    fun extractEmail(token: String): String? = getAllClaims(token).subject

    fun isExpired(token: String): Boolean = getAllClaims(token).expiration.before(Date(System.currentTimeMillis()))

    fun isValid(token: String, userDetails: UserDetails): Boolean {
        val email = extractEmail(token)
        return userDetails.username == email && !isExpired(token)
    }

    private fun getAllClaims(token: String): Claims {
        val parser = Jwts.parser().verifyWith(secreatKey).build()
        return parser.parseSignedClaims(token).payload
    }
}