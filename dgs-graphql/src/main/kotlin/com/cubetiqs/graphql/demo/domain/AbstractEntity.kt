package com.cubetiqs.graphql.demo.domain

import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.io.Serializable
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class AbstractEntity<T, ID : Serializable> : Serializable