package com.cubetiqs.graphql.demo.resolver.mutation

import com.cubetiqs.graphql.demo.context.GMutation
import com.cubetiqs.graphql.demo.dgmodel.DgsConstants
import com.cubetiqs.graphql.demo.dgmodel.types.LoginResponse
import com.cubetiqs.graphql.demo.security.AuthService
import com.cubetiqs.sp.security.jwt.util.JwtTokenUtils
import com.netflix.graphql.dgs.DgsMutation
import org.springframework.beans.factory.annotation.Autowired

@GMutation
class LoginMutationResolver {
    @Autowired
    private lateinit var authService: AuthService

    @DgsMutation(field = DgsConstants.MUTATION.Login)
    fun login(username: String, password: String): LoginResponse {
        val auth = authService.login(username, password)
        val token = JwtTokenUtils.createTokens(auth)
        return LoginResponse(
            accessToken = token.accessToken,
            refreshToken = token.refreshToken,
        )
    }
}