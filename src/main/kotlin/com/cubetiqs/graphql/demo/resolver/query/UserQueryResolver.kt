package com.cubetiqs.graphql.demo.resolver.query

import com.cubetiqs.graphql.demo.context.GQuery
import com.cubetiqs.graphql.demo.domain.user.User
import com.cubetiqs.graphql.demo.repository.UserRepository
import graphql.kickstart.tools.GraphQLQueryResolver
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable

@GQuery
class UserQueryResolver @Autowired constructor(
    private val userRepository: UserRepository,
) : GraphQLQueryResolver {
    fun fetchUsers(): Collection<User> {
        val users = userRepository.queryAllByEnabledIsTrue(Pageable.unpaged())
        return users.content
    }
}