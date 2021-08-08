package com.cubetiqs.graphql.demo.resolver.subscription

import com.cubetiqs.graphql.demo.context.GSubscription
import com.cubetiqs.graphql.demo.domain.account.Account
import com.cubetiqs.graphql.demo.repository.AccountRepository
import com.netflix.graphql.dgs.DgsSubscription
import graphql.schema.DataFetchingEnvironment
import kotlinx.coroutines.reactor.awaitSingle
import org.reactivestreams.Publisher
import org.springframework.beans.factory.annotation.Autowired
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toFlux
import reactor.kotlin.core.publisher.toMono
import java.time.Duration

@GSubscription
class HelloSubscriptionResolver {
    @Autowired
    private lateinit var accountRepository: AccountRepository

    @DgsSubscription(field = "hello")
    fun hello(env: DataFetchingEnvironment): Publisher<Int> {
        return Flux.range(1, 10).delayElements(Duration.ofSeconds(1))
    }

    @DgsSubscription(field = "fetchAccounts")
    fun fetchAccounts(env: DataFetchingEnvironment): Publisher<Collection<Account>> {
        return Flux.generate<Collection<Account>?> { sink ->
            sink.next(accountRepository.findAll())
        }.delayElements(Duration.ofSeconds(1))
        // return Mono.fromFuture(accounts).toFlux().delayElements(Duration.ofSeconds(1))
    }
}