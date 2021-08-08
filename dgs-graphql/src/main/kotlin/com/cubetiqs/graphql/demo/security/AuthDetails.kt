package com.cubetiqs.graphql.demo.security

import com.cubetiqs.graphql.demo.domain.user.User
import com.cubetiqs.security.jwt.util.JwtUtils
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

data class AuthDetails(
    private var code: String? = null,
    private var name: String? = null,
    private var username: String? = null,
    private var password: String? = null,
    private var authorities: Collection<String>? = null,
    private var enabled: Boolean? = null,
) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return authorities?.map { SimpleGrantedAuthority(it) }?.toMutableList() ?: mutableListOf(
            SimpleGrantedAuthority(
                "USER"
            )
        )
    }

    override fun getPassword(): String {
        return password ?: ""
    }

    override fun getUsername(): String {
        return username ?: ""
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return enabled ?: false
    }

    fun isPasswordValid(password: String): Boolean {
        return JwtUtils.passwordEncoder().matches(password, this.getPassword())
    }

    companion object {
        fun fromUser(user: User): AuthDetails {
            return AuthDetails(
                code = user.code,
                name = user.name,
                username = user.username,
                password = user.password,
                enabled = user.enabled,
            )
        }
    }
}