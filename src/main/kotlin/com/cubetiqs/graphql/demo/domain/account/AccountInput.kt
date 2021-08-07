package com.cubetiqs.graphql.demo.domain.account

data class AccountInput(
    var userId: Long? = null,
    var type: AccountType? = null,
    var currency: AccountCurrency? = null,
)