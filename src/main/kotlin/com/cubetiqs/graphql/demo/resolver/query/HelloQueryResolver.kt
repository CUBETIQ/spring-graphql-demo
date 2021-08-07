package com.cubetiqs.graphql.demo.resolver.query

import com.cubetiqs.graphql.demo.context.GQuery
import com.netflix.graphql.dgs.DgsQuery
import reactor.core.publisher.Mono
import java.util.concurrent.CompletableFuture

@GQuery
class HelloQueryResolver {
    @DgsQuery(field = "hello")
    fun hello(): CompletableFuture<String> {
        return Mono.just("Hello Query...!").toFuture()
    }

    @DgsQuery(field = "helloByName")
    fun helloByName(name: String): CompletableFuture<String> {
        return Mono.just("Hello $name...!").toFuture()
    }
}