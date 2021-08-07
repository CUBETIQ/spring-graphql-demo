package com.cubetiqs.graphql.demo.mutation

import com.cubetiqs.graphql.demo.context.GMutation
import com.cubetiqs.graphql.demo.domain.account.Account
import com.cubetiqs.graphql.demo.domain.account.AccountInput
import com.cubetiqs.graphql.demo.domain.account.AccountMapper
import com.cubetiqs.graphql.demo.repository.AccountRepository
import com.cubetiqs.graphql.demo.repository.UserRepository
import graphql.kickstart.tools.GraphQLMutationResolver
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@GMutation
class AccountMutation @Autowired constructor(
    private val accountRepository: AccountRepository,
    private val userRepository: UserRepository,
) : GraphQLMutationResolver {
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    fun openAccount(input: AccountInput): Account {
        val account = AccountMapper.fromInputToAccount(input)
        val user = userRepository.findById(input.userId ?: 0).orElse(null) ?: throw Exception("User not found to open an account!")
        account.user = user
        return accountRepository.save(account)
    }
}