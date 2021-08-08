package com.cubetiqs.graphql.demo.resolver.mutation

import com.cubetiqs.graphql.demo.context.GMutation
import com.cubetiqs.graphql.demo.dgmodel.DgsConstants
import com.cubetiqs.graphql.demo.domain.account.Account
import com.cubetiqs.graphql.demo.domain.account.AccountInput
import com.cubetiqs.graphql.demo.domain.account.AccountMapper
import com.cubetiqs.graphql.demo.repository.AccountRepository
import com.cubetiqs.graphql.demo.repository.UserRepository
import com.netflix.graphql.dgs.DgsMutation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@GMutation
class AccountMutationResolver {
    @Autowired
    private lateinit var accountRepository: AccountRepository

    @Autowired
    private lateinit var userRepository: UserRepository

    @DgsMutation(field = DgsConstants.MUTATION.OpenAccount)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    fun openAccount(input: AccountInput): Account {
        val account = AccountMapper.fromInputToAccount(input)
        val user = userRepository.findById(input.userId ?: 0).orElse(null)
            ?: throw Exception("User not found to open an account!")
        account.user = user
        return accountRepository.save(account)
    }
}