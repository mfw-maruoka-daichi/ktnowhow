package com.moneyforward.ktnowhow.model

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

sealed class User {
    abstract val id: ID
    abstract val name: String
    abstract val iconUrl: String?

//    @OptIn(ExperimentalContracts::class)
//    fun isIdDefined(): Boolean {
//        contract {
//            returns(true) implies (this@User is DefinedUser)
//            returns(false) implies (this@User is UndefinedUser)
//        }
//        return id.isDefined
//    }
}

@OptIn(ExperimentalContracts::class)
fun User.isIdDefined(): Boolean {
    contract {
        returns(true) implies (this@isIdDefined is DefinedUser)
        returns(false) implies (this@isIdDefined is UndefinedUser)
    }
    return id.isDefined
}

data class UndefinedUser(
    override val name: String,
    override val iconUrl: String?
) : User() {
    override val id: UndefinedID = UndefinedID
}

data class DefinedUser(
    val rawId: Long,
    override val name: String,
    override val iconUrl: String?
) : User() {
    override val id: DefinedID<Long> = DefinedID(rawId)
}
