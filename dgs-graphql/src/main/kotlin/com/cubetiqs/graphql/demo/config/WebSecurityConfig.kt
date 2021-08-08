package com.cubetiqs.graphql.demo.config

import com.cubetiqs.graphql.demo.security.AuthService
import com.cubetiqs.security.jwt.AuthenticationExceptionEntryPoint
import com.cubetiqs.security.jwt.JwtSecurityConfigurer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class WebSecurityConfig : WebSecurityConfigurerAdapter() {
    @Autowired
    private lateinit var authService: AuthService

    override fun configure(http: HttpSecurity) {
        http.csrf().disable()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

        http
            .exceptionHandling()
            .authenticationEntryPoint(AuthenticationExceptionEntryPoint())

        http
            .apply(JwtSecurityConfigurer(authService))

        http
            .authorizeRequests()
            .anyRequest().permitAll()
    }
}