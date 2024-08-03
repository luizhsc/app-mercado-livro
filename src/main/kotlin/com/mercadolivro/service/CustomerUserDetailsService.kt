package com.mercadolivro.service

import com.mercadolivro.exception.AuthenticationException
import com.mercadolivro.model.CustomerModel
import com.mercadolivro.repository.CustomerRepository
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

typealias ApplicationUser = com.mercadolivro.model.CustomerModel

@Service
class CustomerUserDetailsService(
    private val customerRepository: CustomerRepository
) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails =
        mapToUserDetails( customerRepository.findByEmail(username)
            .orElseThrow({ AuthenticationException("998", "Usuário não encontrado") }))

    private fun mapToUserDetails(customer: CustomerModel): UserDetails =
        User.builder()
            .username(customer?.email)
            .password(customer?.password)
            .roles(customer?.roles.toString())
            .build()

}