package com.moneyforward.ktnowhow.graphql.extension.id

import com.expediagroup.graphql.generator.annotations.GraphQLName
import com.expediagroup.graphql.generator.scalars.ID
import com.moneyforward.ktnowhow.graphql.extention.id.getRawId
import com.moneyforward.ktnowhow.graphql.extention.id.toID
import com.moneyforward.ktnowhow.graphql.type.LongIdType
import graphql.relay.Relay
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class IdFeatureTest : FunSpec() {
    @GraphQLName("Annotated")
    private class AnnotatedType(override val rawId: Long) : LongIdType
    private class NonAnnotatedType(override val rawId: Long) : LongIdType

    init {
        context("normal") {
            context("toID") {
                test("to annotated type ID") {
                    1L.toID(AnnotatedType::class) shouldBe ID(Relay().toGlobalId("Annotated", "1"))
                }
                test("to non annotated type ID") {
                    1L.toID(NonAnnotatedType::class) shouldBe ID(Relay().toGlobalId("NonAnnotatedType", "1"))
                }
            }
            context("getRawId") {
                test("from annotated type ID") {
                    ID(Relay().toGlobalId("Annotated", "1")).getRawId(AnnotatedType::class) shouldBe 1L
                }
                test("from non annotated type ID") {
                    ID(Relay().toGlobalId("NonAnnotatedType", "1")).getRawId(NonAnnotatedType::class) shouldBe 1L
                }
            }
        }
        context("error") {
            context("toID") {
                // todo test("return null") when KClass.simpleName is null. (overrideできないので手段あれば)
            }
            context("getRawId") {
                test("illegal ID format") {
                    ID("first:second:third").getRawId(NonAnnotatedType::class) shouldBe null
                }
                test("mismatch GraphQL type name") {
                    ID(Relay().toGlobalId("NotGraphqlTypeName", "1")).getRawId(NonAnnotatedType::class) shouldBe null
                }
                test("mismatch GraphQL type name with annotation") {
                    ID(Relay().toGlobalId("AnnotatedType", "1")).getRawId(AnnotatedType::class) shouldBe null
                }
                test("not Long value") {
                    ID(Relay().toGlobalId("NonAnnotatedType", "str")).getRawId(NonAnnotatedType::class) shouldBe null
                }
            }
        }
    }
}