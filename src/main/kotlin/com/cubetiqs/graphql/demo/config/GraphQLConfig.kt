package com.cubetiqs.graphql.demo.config

import graphql.scalars.ExtendedScalars
import graphql.schema.idl.RuntimeWiring
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class GraphQLConfig {
    @Bean
    fun extendsScalars(): RuntimeWiring.Builder {
        return RuntimeWiring.newRuntimeWiring()
            .scalar(ExtendedScalars.DateTime)
    }
}