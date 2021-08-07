package com.cubetiqs.graphql.demo.resolver.query

import com.cubetiqs.graphql.demo.context.GQuery
import com.cubetiqs.graphql.demo.domain.account.Account
import com.cubetiqs.graphql.demo.repository.AccountRepository
import graphql.kickstart.tools.GraphQLQueryResolver
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable

@GQuery
class AccountQueryResolver @Autowired constructor(
    private val accountRepository: AccountRepository,
) : GraphQLQueryResolver {
    fun fetchAccounts(): Collection<Account> {
        val accounts = accountRepository.findAll(Pageable.unpaged())
        return accounts.content
    }
}