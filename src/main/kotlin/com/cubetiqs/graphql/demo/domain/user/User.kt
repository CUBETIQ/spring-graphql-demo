package com.cubetiqs.graphql.demo.domain.user

import com.cubetiqs.graphql.demo.domain.AbstractEntity
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.util.*
import javax.persistence.*

@Entity
@Table(
    name = "users", indexes = [
        Index(name = "idx_user_id", columnList = "id"),
        Index(name = "idx_user_code", columnList = "code"),
        Index(name = "idx_user_username", columnList = "username"),
    ]
)
@EntityListeners(UserEntityListener::class)
open class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(unique = true, length = 65, nullable = false)
    var code: String? = null,

    @Column(unique = true, length = 35, nullable = false)
    var username: String? = null,

    @Column(length = 100)
    var password: String? = null,

    @Column(length = 50)
    var name: String? = null,

    @Version
    var version: Long? = null,

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    var createdDate: Date? = null,

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    var updatedDate: Date? = null,
) : AbstractEntity<User, Long>() {
    @Transient
    fun updatePassword(newPassword: String) {
        // hash it here
        this.password = newPassword
    }
}