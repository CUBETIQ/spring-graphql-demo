package com.cubetiqs.graphql.demo.resolver.mutation

import com.cubetiqs.graphql.demo.context.GMutation
import com.cubetiqs.graphql.demo.dgmodel.DgsConstants
import com.cubetiqs.graphql.demo.dgmodel.types.UserChangePasswordInput
import com.cubetiqs.graphql.demo.domain.user.User
import com.cubetiqs.graphql.demo.domain.user.UserInput
import com.cubetiqs.graphql.demo.domain.user.UserMapper
import com.cubetiqs.graphql.demo.repository.UserRepository
import com.cubetiqs.sp.security.util.PasswordUtils
import com.netflix.graphql.dgs.DgsMutation
import com.netflix.graphql.dgs.exceptions.DgsEntityNotFoundException
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
        if (userRepository.existsAllByUsername(input.username ?: "")) throw DgsEntityNotFoundException("Username has been already existed!")

        val user = UserMapper.fromInputToUser(input)
        return userRepository.save(user)
    }

    @DgsMutation(field = DgsConstants.MUTATION.ChangeUserPassword)
    fun changePassword(input: UserChangePasswordInput): User {
        val user = userRepository.queryByUsername(input.username).orElse(null) ?: throw DgsEntityNotFoundException("User not found!")
        user.password = PasswordUtils.encode(input.password)
        return userRepository.save(user)
    }
}