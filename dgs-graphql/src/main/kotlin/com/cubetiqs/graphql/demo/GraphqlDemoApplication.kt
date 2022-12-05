package com.cubetiqs.graphql.demo

import com.cubetiqs.sp.security.util.PasswordUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.security.crypto.password.PasswordEncoder

@SpringBootApplication
class GraphqlDemoApplication @Autowired constructor(
    private val passwordEncoder: PasswordEncoder,
) {
    init {
        PasswordUtils.setEncoder(passwordEncoder)
    }
}

fun main(args: Array<String>) {
    runApplication<GraphqlDemoApplication>(*args)
}
