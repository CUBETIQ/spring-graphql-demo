package com.cubetiqs.graphql.demo.domain.account

import java.util.*
import jakarta.persistence.PostPersist
import jakarta.persistence.PrePersist
import jakarta.persistence.PreUpdate

class AccountEntityListener {
    @PrePersist
    fun beforeSave(account: Account) {
        if (account.createdDate == null) {
            account.createdDate = Date()
        }
    }

    @PreUpdate
    fun beforeUpdate(account: Account) {
        account.updatedDate = Date()
    }

    @PostPersist
    fun afterSaved(account: Account) {
        account.code = account.id.toString().padStart(9, '0')
    }
}