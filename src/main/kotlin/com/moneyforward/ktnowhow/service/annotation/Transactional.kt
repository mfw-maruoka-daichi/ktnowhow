package com.moneyforward.ktnowhow.service.annotation

import kotlin.reflect.KClass
import org.springframework.core.annotation.AliasFor
import org.springframework.transaction.TransactionDefinition
import org.springframework.transaction.annotation.Isolation
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional as SpringDefaultTransactional

@Target(AnnotationTarget.TYPE, AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@SpringDefaultTransactional
annotation class Transactional(
    @get:AliasFor(annotation = SpringDefaultTransactional::class, attribute = "value")
    val value: String = "",

    @get:AliasFor(annotation = SpringDefaultTransactional::class, attribute = "transactionManager")
    val transactionManager: String = "",

    @get:AliasFor(annotation = SpringDefaultTransactional::class, attribute = "label")
    val label: Array<String> = [],

    @get:AliasFor(annotation = SpringDefaultTransactional::class, attribute = "propagation")
    val propagation: Propagation = Propagation.REQUIRED,

    @get:AliasFor(annotation = SpringDefaultTransactional::class, attribute = "isolation")
    val isolation: Isolation = Isolation.DEFAULT,

    @get:AliasFor(annotation = SpringDefaultTransactional::class, attribute = "timeout")
    val timeout: Int = TransactionDefinition.TIMEOUT_DEFAULT,

    @get:AliasFor(annotation = SpringDefaultTransactional::class, attribute = "timeoutString")
    val timeoutString: String = "",

    @get:AliasFor(annotation = SpringDefaultTransactional::class, attribute = "readOnly")
    val readOnly: Boolean = false,

    @get:AliasFor(annotation = SpringDefaultTransactional::class, attribute = "rollbackFor")
    val rollbackFor: Array<KClass<out Throwable>> = [Exception::class],

    @get:AliasFor(annotation = SpringDefaultTransactional::class, attribute = "rollbackForClassName")
    val rollbackForClassName: Array<String> = [],

    @get:AliasFor(annotation = SpringDefaultTransactional::class, attribute = "noRollbackFor")
    val noRollbackFor: Array<KClass<out Throwable>> = [],

    @get:AliasFor(annotation = SpringDefaultTransactional::class, attribute = "noRollbackForClassName")
    val noRollbackForClassName: Array<String> = []
)
