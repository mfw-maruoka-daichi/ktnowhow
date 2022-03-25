package com.moneyforward.ktnowhow.model

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

data class User<T : ID>(
    val id: T,
    val name: String,
    val iconUrl: String?,
) {
    @OptIn(ExperimentalContracts::class)
    fun isIDDefined(): Boolean {
        contract {
            // compile error:: Cannot check for instance of erased type: User<DefinedID>
            returns(true) implies (this@User is User<DefinedID<T>>)
            // compile error:: Cannot check for instance of erased type: User<UndefinedID>
            returns(false) implies (this@User is User<UndefinedID>)
        }

        return id.isDefined
    }
}
