package com.cubetiqs.graphql.demo.resolver.mutation

import com.cubetiqs.graphql.demo.context.GMutation
import com.cubetiqs.graphql.demo.dgmodel.DgsConstants
import com.cubetiqs.graphql.demo.dgmodel.types.LoginResponse
import com.cubetiqs.graphql.demo.security.AuthService
import com.cubetiqs.security.jwt.util.JwtUtils
import com.netflix.graphql.dgs.DgsMutation
import org.springframework.beans.factory.annotation.Autowired

@GMutation
class LoginMutationResolver {
    @Autowired
    private lateinit var authService: AuthService

    @DgsMutation(field = DgsConstants.MUTATION.Login)
    fun login(username: String, password: String): LoginResponse {
        val auth = authService.login(username, password)
        val token = JwtUtils.encryptToken(auth)
        return LoginResponse(token)
    }
}