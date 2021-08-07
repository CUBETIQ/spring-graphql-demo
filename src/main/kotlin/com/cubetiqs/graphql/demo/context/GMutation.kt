package com.cubetiqs.graphql.demo.context

import org.springframework.core.annotation.AliasFor
import org.springframework.stereotype.Component

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
@Component
annotation class GMutation(
    @get:AliasFor(annotation = Component::class)
    val value: String = "",
)
