package com.cubetiqs.graphql.demo.domain.account

import com.cubetiqs.graphql.demo.domain.AbstractEntity
import com.cubetiqs.graphql.demo.domain.user.User
import com.fasterxml.jackson.annotation.JsonBackReference
import org.hibernate.Hibernate
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.math.BigDecimal
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "accounts", indexes = [
    Index(name = "idx_account_code", columnList = "code")
])
@EntityListeners(value = [AccountEntityListener::class])
open class Account(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open var id: Long? = null,

    @Column(length = 9)
    open var code: String? = null,

    @Column
    open var balance: BigDecimal = BigDecimal.ZERO,

    @Column
    open var currentBalance: BigDecimal = BigDecimal.ZERO,

    @Column(length = 10)
    @Enumerated(EnumType.STRING)
    open var type: AccountType = AccountType.BASIC,

    @Column(length = 3)
    @Enumerated(EnumType.STRING)
    open var currency: AccountCurrency = AccountCurrency.USD,

    @Version
    open var version: Long? = null,

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    open var createdDate: Date? = null,

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    open var updatedDate: Date? = null,

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY,cascade = [CascadeType.REFRESH, CascadeType.DETACH])
    @JoinColumn(name = "user_id")
    open var user: User? = null,
) : AbstractEntity<Account, Long>() {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Account

        return id != null && id == other.id
    }

    override fun hashCode(): Int = 2083479647

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id , balance = $balance , currentBalance = $currentBalance , type = $type , currency = $currency , version = $version , createdDate = $createdDate , updatedDate = $updatedDate , user = ${user?.id} )"
    }
}