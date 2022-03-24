package com.moneyforward.ktnowhow.model

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

sealed class ID {
    @Suppress("PropertyName")
    protected abstract val _isDefined: Boolean

    @OptIn(ExperimentalContracts::class)
    fun isDefined(): Boolean {
        contract {
            returns(true) implies (this@ID is DefinedID)
            returns(false) implies (this@ID is UndefinedID)
        }
        return _isDefined
    }
}

object UndefinedID : ID() {
    override val _isDefined = false
}

data class DefinedID(val value: Long) : ID() {
    override val _isDefined = true
}
