package com.cubetiqs.graphql.demo.domain.user

object UserMapper {
    fun fromInputToUser(input: UserInput): User {
        return User(
            username = input.username,
            password = input.password,
            name = input.name,
            enabled = input.enabled ?: true,
        )
    }
}