package com.moneyforward.ktnowhow.extention

import com.expediagroup.graphql.generator.scalars.ID

private const val delimiter = ":"

fun ID.combine(rawId: Any): ID = ID("${this.value}$delimiter$rawId")
fun ID.parse(): String = this.value.split(delimiter).last()