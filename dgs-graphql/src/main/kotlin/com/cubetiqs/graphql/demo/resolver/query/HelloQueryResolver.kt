package com.cubetiqs.graphql.demo.resolver.query

import com.cubetiqs.graphql.demo.context.GQuery
import com.cubetiqs.graphql.demo.dgmodel.DgsConstants
import com.netflix.graphql.dgs.DgsQuery
import org.springframework.security.access.prepost.PreAuthorize
import reactor.core.publisher.Mono
import java.util.concurrent.CompletableFuture

@GQuery
class HelloQueryResolver {
    @DgsQuery(field = "hello")
    fun hello(): CompletableFuture<String> {
        return Mono.just("Hello Query...!").toFuture()
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DgsQuery(field = DgsConstants.QUERY.HelloByName)
    fun helloByName(name: String): CompletableFuture<String> {
        return Mono.just("Hello $name...!").toFuture()
    }
}