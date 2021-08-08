package com.cubetiqs.graphql.demo.security

import com.cubetiqs.graphql.demo.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class AuthService : UserDetailsService {
    @Autowired
    private lateinit var userRepository: UserRepository

    private fun findAuthDetailsByUsername(username: String): AuthDetails {
        val user = userRepository.queryByUsername(username).orElse(null) ?: throw Exception("User not found!")
        return AuthDetails.fromUser(user)
    }

    override fun loadUserByUsername(username: String?): UserDetails {
        return findAuthDetailsByUsername(username ?: "")
    }

    fun login(username: String, password: String): AuthDetails {
        val auth = findAuthDetailsByUsername(username)
        if (auth.isPasswordValid(password)) {
            return auth
        } else {
            throw AccessDeniedException("Username and password is incorrect!")
        }
    }
}