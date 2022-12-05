package com.cubetiqs.graphql.demo.config

import com.cubetiqs.graphql.demo.security.AuthService
import com.cubetiqs.sp.security.EnableCubetiqSecurityModule
import com.cubetiqs.sp.security.jwt.CubetiqJwtProperties
import com.cubetiqs.sp.security.jwt.JwtSecurityConfigurer
import com.cubetiqs.sp.security.support.AuthenticationExceptionEntryPoint
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
@EnableCubetiqSecurityModule
class WebSecurityConfig @Autowired constructor(
    private val authService: AuthService,
    private val cubetiqJwtProperties: CubetiqJwtProperties,
) {
    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder(10)
    }

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

        http.exceptionHandling().authenticationEntryPoint(AuthenticationExceptionEntryPoint())

        http.apply(JwtSecurityConfigurer(authService, cubetiqJwtProperties))

        http.authorizeHttpRequests().anyRequest().permitAll()

        return http.build()
    }
}