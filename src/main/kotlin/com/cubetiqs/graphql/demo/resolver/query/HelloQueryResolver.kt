package com.cubetiqs.graphql.demo.resolver.query

import com.cubetiqs.graphql.demo.context.GQuery
import graphql.kickstart.tools.GraphQLQueryResolver
import reactor.core.publisher.Mono
import java.util.concurrent.CompletableFuture

@GQuery
class HelloQueryResolver : GraphQLQueryResolver {
    fun hello(): CompletableFuture<String> {
        return Mono.just("Hello Query...!").toFuture()
    }
}