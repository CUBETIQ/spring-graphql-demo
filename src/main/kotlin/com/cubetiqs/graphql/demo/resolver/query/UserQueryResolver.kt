package com.cubetiqs.graphql.demo.resolver.query

import com.cubetiqs.graphql.demo.context.GQuery
import com.cubetiqs.graphql.demo.dgmodel.DgsConstants
import com.cubetiqs.graphql.demo.domain.user.User
import com.cubetiqs.graphql.demo.repository.UserRepository
import com.netflix.graphql.dgs.DgsQuery
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable

@GQuery
class UserQueryResolver @Autowired constructor(
    private val userRepository: UserRepository,
) {
    @DgsQuery(field = DgsConstants.QUERY.FetchUsers)
    fun fetchUsers(): Collection<User> {
        val users = userRepository.queryAllByEnabledIsTrue(Pageable.unpaged())
        return users.content
    }
}