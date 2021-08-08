package com.cubetiqs.graphql.demo.repository

import com.cubetiqs.graphql.demo.domain.account.Account
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Repository
import java.util.concurrent.CompletableFuture

@Repository
interface AccountRepository : JpaRepository<Account, Long> {
    @Async
    fun readAllBy(): CompletableFuture<Collection<Account>>
}