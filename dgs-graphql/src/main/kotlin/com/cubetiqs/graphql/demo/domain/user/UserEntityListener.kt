package com.cubetiqs.graphql.demo.domain.user

import java.util.*
import jakarta.persistence.PrePersist
import jakarta.persistence.PreUpdate

class UserEntityListener {
    @PrePersist
    fun beforeSave(user: User) {
        user.id = null
        user.code = UUID.randomUUID().toString()

        if (user.createdDate == null) {
            user.createdDate = Date()
        }
    }

    @PreUpdate
    fun beforeUpdate(user: User) {
        user.updatedDate = Date()
    }
}