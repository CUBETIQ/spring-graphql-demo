package com.cubetiqs.graphql.demo.domain.account

object AccountMapper {
    fun fromInputToAccount(input: AccountInput): Account {
        return Account(
            type = input.type ?: AccountType.BASIC,
            currency = input.currency ?: AccountCurrency.USD,
        )
    }
}