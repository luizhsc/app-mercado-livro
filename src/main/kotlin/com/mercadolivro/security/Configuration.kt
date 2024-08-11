package com.mercadolivro.security

import com.mercadolivro.repository.CustomerRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@Configuration
class Configuration {

    @Bean
    fun userDetailsService(customerRepository: CustomerRepository): UserDetailsService =
        CustomerUserDetailsService(customerRepository)

    @Bean
    fun enconder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun authenticationProvider(customerRepository: CustomerRepository): AuthenticationProvider =
        DaoAuthenticationProvider()
            .also {
                it.setUserDetailsService(userDetailsService(customerRepository))
                it.setPasswordEncoder(enconder())
            }

    @Bean
    fun authenticationManger(config: AuthenticationConfiguration): AuthenticationManager =
        config.authenticationManager


}