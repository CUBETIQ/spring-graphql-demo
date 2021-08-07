package com.cubetiqs.graphql.demo.domain.account

import com.cubetiqs.graphql.demo.domain.AbstractEntity
import org.hibernate.Hibernate
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.math.BigDecimal
import java.util.*
import javax.persistence.*

@Entity
@Table(
    name = "accounts", indexes = [
        Index(name = "idx_account_id", columnList = "id")
    ]
)
@EntityListeners(AccountEntityListener::class)
open class Account(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column
    var balance: BigDecimal = BigDecimal.ZERO,

    @Column
    var currentBalance: BigDecimal = BigDecimal.ZERO,

    @Column(length = 10)
    @Enumerated(EnumType.STRING)
    var accountType: AccountType = AccountType.BASIC,

    @Column(length = 3)
    @Enumerated(EnumType.STRING)
    var currency: AccountCurrency = AccountCurrency.USD,

    @Version
    var version: Long? = null,

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    var createdDate: Date? = null,

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    var updatedDate: Date? = null,
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
        return this::class.simpleName + "(id = $id , balance = $balance , currentBalance = $currentBalance , accountType = $accountType , currency = $currency , version = $version , createdDate = $createdDate , updatedDate = $updatedDate )"
    }
}