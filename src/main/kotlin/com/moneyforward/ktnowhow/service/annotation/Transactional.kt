package com.moneyforward.ktnowhow.service.annotation

import kotlin.reflect.KClass
import org.springframework.core.annotation.AliasFor
import org.springframework.transaction.annotation.Transactional as SpringDefaultTransactional

@Target(AnnotationTarget.TYPE, AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@SpringDefaultTransactional
annotation class Transactional(
    @get:AliasFor(annotation = SpringDefaultTransactional::class, attribute = "rollbackFor")
    val rollbackFor: Array<KClass<out Throwable>> = [Exception::class]
)
