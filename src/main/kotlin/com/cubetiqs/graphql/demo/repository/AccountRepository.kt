package com.cubetiqs.graphql.demo.repository

import com.cubetiqs.graphql.demo.domain.account.Account
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AccountRepository : JpaRepository<Account, Long>