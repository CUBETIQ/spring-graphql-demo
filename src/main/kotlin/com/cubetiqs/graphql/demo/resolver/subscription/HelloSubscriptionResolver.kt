package com.cubetiqs.graphql.demo.resolver.subscription

import graphql.kickstart.tools.GraphQLSubscriptionResolver
import graphql.schema.DataFetchingEnvironment
import org.reactivestreams.Publisher
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import java.time.Duration

@Service
class HelloSubscriptionResolver : GraphQLSubscriptionResolver {
    fun hello(env: DataFetchingEnvironment): Publisher<Int> {
        return Flux.range(1, 10).delayElements(Duration.ofSeconds(1))
    }
}