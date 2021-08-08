package com.cubetiqs.graphql.demo.resolver.mutation

import com.cubetiqs.graphql.demo.context.GMutation
import com.cubetiqs.graphql.demo.dgmodel.DgsConstants
import com.cubetiqs.graphql.demo.domain.user.User
import com.cubetiqs.graphql.demo.domain.user.UserInput
import com.cubetiqs.graphql.demo.domain.user.UserMapper
import com.cubetiqs.graphql.demo.repository.UserRepository
import com.netflix.graphql.dgs.DgsMutation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@GMutation
class UserMutationResolver @Autowired constructor(
    private val userRepository: UserRepository,
) {
    @DgsMutation(field = DgsConstants.MUTATION.CreateUser)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    fun createUser(input: UserInput): User {
        if (userRepository.existsAllByUsername(input.username ?: "")) throw Exception("Username has been already existed!")

        val user = UserMapper.fromInputToUser(input)
        return userRepository.save(user)
    }
}