package com.moneyforward.ktnowhow.graphql.subscription

import com.expediagroup.graphql.generator.scalars.ID
import com.expediagroup.graphql.server.operations.Subscription
import com.moneyforward.ktnowhow.graphql.type.Knowhow
import org.reactivestreams.Publisher
import org.springframework.stereotype.Component

@Component
class KnowhowSubscription : Subscription {
    fun knowhowChanges(id: ID): Publisher<Knowhow> = TODO("Not yet implemented")
}
