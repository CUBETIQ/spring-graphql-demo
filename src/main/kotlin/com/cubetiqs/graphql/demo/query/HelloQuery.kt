package com.cubetiqs.graphql.demo.query

import com.cubetiqs.graphql.demo.context.GQuery
import graphql.kickstart.tools.GraphQLQueryResolver
import reactor.core.publisher.Mono
import java.util.concurrent.CompletableFuture

@GQuery
class HelloQuery : GraphQLQueryResolver {
    fun hello(): CompletableFuture<String> {
        return Mono.just("Hello Query...!").toFuture()
    }
}