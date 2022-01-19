package com.moneyforward.ktnowhow

import org.apache.commons.logging.LogFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KtnowhowApplication

fun main(args: Array<String>) {
    runApplication<KtnowhowApplication>(*args)
    LogFactory.getLog(KtnowhowApplication::class.java)
        .info("default GraphQL Playground URL: http://localhost:8080/graphql")
}
