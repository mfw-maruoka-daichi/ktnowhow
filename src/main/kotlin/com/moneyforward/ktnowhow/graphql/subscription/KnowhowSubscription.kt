package com.moneyforward.ktnowhow.graphql.subscription

import com.expediagroup.graphql.generator.scalars.ID
import com.expediagroup.graphql.server.operations.Subscription
import com.moneyforward.ktnowhow.graphql.type.KnowhowType
import org.reactivestreams.Publisher
import org.springframework.stereotype.Component

@Component
class KnowhowSubscription : Subscription {
    fun knowhowChanges(id: ID): Publisher<KnowhowType> = TODO("Not yet implemented")
}
