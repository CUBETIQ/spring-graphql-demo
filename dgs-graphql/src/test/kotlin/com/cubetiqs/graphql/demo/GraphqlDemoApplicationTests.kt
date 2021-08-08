package com.cubetiqs.graphql.demo

import com.cubetiqs.graphql.demo.resolver.subscription.HelloSubscriptionResolver
import com.netflix.graphql.dgs.DgsQueryExecutor
import com.netflix.graphql.dgs.autoconfig.DgsAutoConfiguration
import graphql.ExecutionResult
import org.junit.jupiter.api.Test
import org.reactivestreams.Publisher
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(
    classes = [
        DgsAutoConfiguration::class,
        HelloSubscriptionResolver::class,
    ]
)
class GraphqlDemoApplicationTests {
    @Autowired
    lateinit var dgsQueryExecutor: DgsQueryExecutor

    @Test
    fun helloSubscription() {
        dgsQueryExecutor.execute(
            """
            subscription {
              hello
            }
        """.trimIndent()
        )
            .getData<Publisher<ExecutionResult>>()
            .subscribe(object : Subscriber<ExecutionResult> {
                override fun onSubscribe(s: Subscription) {
                    s.request(2)
                }

                override fun onNext(t: ExecutionResult) {
                    println(t.getData<Any?>())
                }

                override fun onError(t: Throwable?) {

                }

                override fun onComplete() {
                    println("Hello World")
                }
            }

            )
    }

}
