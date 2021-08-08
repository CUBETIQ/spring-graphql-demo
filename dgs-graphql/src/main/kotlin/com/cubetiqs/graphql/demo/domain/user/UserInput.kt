package com.cubetiqs.graphql.demo.domain.user

import com.cubetiqs.graphql.demo.domain.AbstractInput

data class UserInput(
    val username: String? = null,
    val password: String? = null,
    val name: String? = null,
    val enabled: Boolean? = null,
) : AbstractInput<UserInput>()