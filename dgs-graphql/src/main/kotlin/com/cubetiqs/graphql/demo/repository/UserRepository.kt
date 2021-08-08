package com.cubetiqs.graphql.demo.repository

import com.cubetiqs.graphql.demo.domain.user.User
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {
    @Query("select u from User u where u.enabled = true")
    fun queryAllByEnabledIsTrue(pageable: Pageable): Page<User>

    @Query("select (count(u) > 0) from User u where u.username = ?1")
    fun existsAllByUsername(username: String): Boolean
}