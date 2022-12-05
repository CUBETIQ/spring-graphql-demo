package com.cubetiqs.graphql.demo.domain.user

import com.cubetiqs.graphql.demo.domain.AbstractEntity
import com.cubetiqs.graphql.demo.domain.account.Account
import com.fasterxml.jackson.annotation.JsonManagedReference
import org.hibernate.Hibernate
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.util.*
import jakarta.persistence.*

@Entity
@Table(
    name = "users", indexes = [
        Index(name = "idx_user_code", columnList = "code"),
        Index(name = "idx_user_username", columnList = "username"),
    ]
)
@EntityListeners(value = [UserEntityListener::class])
open class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open var id: Long? = null,

    @Column(unique = true, length = 65, nullable = false)
    open var code: String? = null,

    @Column(unique = true, length = 35, nullable = false)
    open var username: String? = null,

    @Column(length = 100)
    open var password: String? = null,

    @Column(length = 50)
    open var name: String? = null,

    @Version
    open var version: Long? = null,

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    open var createdDate: Date? = null,

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    open var updatedDate: Date? = null,

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true)
    open var accounts: MutableSet<Account> = mutableSetOf(),

    @Basic
    open var enabled: Boolean = true,
) : AbstractEntity<User, Long>() {
    @Transient
    fun updatePassword(newPassword: String) {
        // hash it here
        this.password = newPassword
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as User

        return id != null && id == other.id
    }

    override fun hashCode(): Int = 562048007

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id , code = $code , username = $username , password = $password , name = $name , version = $version , createdDate = $createdDate , updatedDate = $updatedDate , enabled = $enabled )"
    }
}