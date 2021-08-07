package com.cubetiqs.graphql.demo.domain.user

import java.util.*
import javax.persistence.PrePersist

class UserEntityListener {
    @PrePersist
    fun beforeSave(user: User) {
        user.code = UUID.randomUUID().toString()
    }
}