package com.moneyforward.ktnowhow.graphql.subscription

import com.expediagroup.graphql.generator.scalars.ID
import com.expediagroup.graphql.server.operations.Subscription
import com.moneyforward.ktnowhow.graphql.type.KnowhowType
import com.moneyforward.ktnowhow.service.KnowhowService
import org.reactivestreams.Publisher
import org.springframework.stereotype.Component

@Component
class KnowhowSubscription(private val knowhowService: KnowhowService) : Subscription {
    fun knowhowChanges(id: ID): Publisher<KnowhowType> = knowhowService.providePublisher(id)
}
