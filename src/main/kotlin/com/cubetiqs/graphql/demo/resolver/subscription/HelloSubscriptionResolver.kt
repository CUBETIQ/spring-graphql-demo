package com.cubetiqs.graphql.demo.resolver.subscription

import com.cubetiqs.graphql.demo.context.GSubscription
import com.netflix.graphql.dgs.DgsSubscription
import graphql.schema.DataFetchingEnvironment
import org.reactivestreams.Publisher
import reactor.core.publisher.Flux
import java.time.Duration

@GSubscription
class HelloSubscriptionResolver {
    @DgsSubscription(field = "hello")
    fun hello(env: DataFetchingEnvironment): Publisher<Int> {
        return Flux.range(1, 10).delayElements(Duration.ofSeconds(1))
    }
}