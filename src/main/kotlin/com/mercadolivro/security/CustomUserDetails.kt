package com.mercadolivro.security

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class CustomUserDetails(
    private val id: String,
    private val username: String,
    private val password: String,
    private val authorities: Collection<GrantedAuthority>
) : UserDetails {

    fun getId(): String {
        return id
    }

    override fun getAuthorities(): Collection<GrantedAuthority> {
        return authorities
    }

    override fun getPassword(): String {
        return password
    }

    override fun getUsername(): String {
        return username
    }

    override fun isAccountNonExpired(): Boolean {
        return true // Pode personalizar a lógica se necessário
    }

    override fun isAccountNonLocked(): Boolean {
        return true // Pode personalizar a lógica se necessário
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true // Pode personalizar a lógica se necessário
    }

    override fun isEnabled(): Boolean {
        return true // Pode personalizar a lógica se necessário
    }

}