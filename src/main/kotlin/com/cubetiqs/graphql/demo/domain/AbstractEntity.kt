package com.cubetiqs.graphql.demo.domain

import java.io.Serializable
import javax.persistence.MappedSuperclass

@MappedSuperclass
abstract class AbstractEntity<T, ID : Serializable> : Serializable