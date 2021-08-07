package com.cubetiqs.graphql.demo.domain.account

import javax.persistence.PrePersist

class AccountEntityListener {
    @PrePersist
    fun beforeSave(account: Account) {

    }
}