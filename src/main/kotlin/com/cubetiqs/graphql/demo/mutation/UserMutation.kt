package com.cubetiqs.graphql.demo.mutation

import com.cubetiqs.graphql.demo.context.GMutation
import com.cubetiqs.graphql.demo.domain.user.User
import com.cubetiqs.graphql.demo.domain.user.UserInput
import com.cubetiqs.graphql.demo.domain.user.UserMapper
import com.cubetiqs.graphql.demo.repository.UserRepository
import graphql.kickstart.tools.GraphQLMutationResolver
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@GMutation
class UserMutation @Autowired constructor(
    private val userRepository: UserRepository,
) : GraphQLMutationResolver {
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    fun createUser(input: UserInput): User {
        if (userRepository.existsAllByUsername(input.username ?: "")) throw Exception("Username has been already existed!")

        val user = UserMapper.fromInputToUser(input)
        return userRepository.save(user)
    }
}