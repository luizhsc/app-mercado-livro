package com.mercadolivro.security

import com.mercadolivro.enums.Errors
import com.mercadolivro.exception.AuthenticationException
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtUtil {

    @Value("\${jwt.expiration}")
    private val expiration: Long? = null

    @Value("\${jwt.secret}")
    private val secret: String? = null

    fun generateToken(
        username: String, additionalClaims: Map<String, Any> = emptyMap()
    ): String =
        Jwts.builder()
            .claims()
            .subject(username)
            .issuedAt(Date(System.currentTimeMillis()))
            .expiration(Date(System.currentTimeMillis() + expiration!!))
            .add(additionalClaims)
            .and()
            .signWith(SignatureAlgorithm.HS256, secret!!.toByteArray())
            .compact()

    fun isValidToken(token: String): Boolean {
        val claims = getClaims(token)
        if(claims.subject == null || claims.expiration == null || Date().after(claims.expiration)) {
            return false
        }
        return true
    }

    private fun getClaims(token: String): Claims {
        try {
            return Jwts.parser().setSigningKey(secret!!.toByteArray()).build().parseClaimsJws(token).body
        } catch (ex : Exception) {
            throw AuthenticationException(Errors.ML997.code, Errors.ML997.message)
        }
    }

    fun getSubject(token: String): String {
        return getClaims(token).subject
    }

}