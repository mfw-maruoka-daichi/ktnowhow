package com.moneyforward.ktnowhow.graphql.extension

import com.expediagroup.graphql.generator.annotations.GraphQLName
import com.expediagroup.graphql.generator.scalars.ID
import com.moneyforward.ktnowhow.graphql.extention.LongIdTypeFeature
import com.moneyforward.ktnowhow.graphql.type.LongIdType
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class LongIdTypeFeatureTest : LongIdTypeFeature, FunSpec() {
    @GraphQLName("Annotated")
    class AnnotatedType(override val rawId: Long) : LongIdType
    class NonAnnotatedType(override val rawId: Long) : LongIdType

    init {
        context("normal") {
            context("toID") {
                test("to annotated type ID") {
                    1L.toID(AnnotatedType::class).shouldBe(ID("Annotated:1"))
                }
                test("to non annotated type ID") {
                    1L.toID(NonAnnotatedType::class).shouldBe(ID("NonAnnotatedType:1"))
                }
            }
            context("getRawId") {
                test("from  annotated type ID") {
                    ID("Annotated:1").getRawId(AnnotatedType::class).shouldBe(1L)
                }
                test("from non annotated type ID") {
                    ID("NonAnnotatedType:1").getRawId(NonAnnotatedType::class).shouldBe(1L)
                }
            }
        }
        context("error") {
            context("toID") {
                // todo test("return null") when KClass.simpleName is null. (overrideできないので手段あれば)
            }
            context("getRawId") {
                test("illegal ID format") {
                    ID("first:second:third").getRawId(NonAnnotatedType::class).shouldBe(null)
                }
                test("mismatch GraphQL type name") {
                    ID("NotGraphqlTypeName:1").getRawId(NonAnnotatedType::class).shouldBe(null)
                }
                test("not Long value") {
                    ID("NonAnnotatedType:notLong").getRawId(NonAnnotatedType::class).shouldBe(null)
                }
            }
        }
    }
}