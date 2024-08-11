package com.mercadolivro.security

import com.mercadolivro.enums.Errors
import com.mercadolivro.exception.AuthenticationException
import com.mercadolivro.repository.CustomerRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class CustomerUserDetailsService(
    private val customerRepository: CustomerRepository
) : UserDetailsService {

    override fun loadUserByUsername(id: String): UserDetails {
        val customer = customerRepository.findById(id.toInt())
            .orElseThrow { AuthenticationException(Errors.ML998.code, Errors.ML998.message) }

        val authorities = customer.roles.map { SimpleGrantedAuthority(it.name) }

        return CustomUserDetails(
            id = customer.id.toString(),
            username = customer.email,
            password = customer.password,
            authorities = authorities
        )

    }

}