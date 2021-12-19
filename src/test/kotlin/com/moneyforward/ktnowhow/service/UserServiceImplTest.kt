package com.moneyforward.ktnowhow.service

import com.expediagroup.graphql.generator.scalars.ID
import com.moneyforward.ktnowhow.db.H2TestDatabase
import com.moneyforward.ktnowhow.db.table.Users
import com.moneyforward.ktnowhow.graphql.type.UserInputType
import com.moneyforward.ktnowhow.graphql.type.UserType
import com.moneyforward.ktnowhow.model.User
import com.moneyforward.ktnowhow.repository.UserRepository
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.Spec
import io.kotest.core.spec.style.ExpectSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

class UserServiceImplTest : ExpectSpec() {

    override fun beforeSpec(spec: Spec) {
        H2TestDatabase.connect()
        transaction {
            SchemaUtils.create(Users)
        }

        super.beforeSpec(spec)
    }

    private val userType1 = UserType(1L, "sample1", null)
    private val userType2 = UserType(2L, "sample2", null)
    private val user1 = with(userType1) { User(rawId, name, iconUrl) }
    private val user2 = with(userType2) { User(rawId, name, iconUrl) }
    private val userRepository = mockk<UserRepository> {
        every { findUserBy(userType1.rawId) } returns user1
        every { findUserBy(userType2.rawId) } returns null
        every { createUser(any()) } returns user1
        every { updateUser(any()) } returns user2
    }
    private val userService = UserServiceImpl(userRepository)

    init {
        context("normal") {
            context("findUserBy") {
                expect("user found") {
                    userService.findUserBy(userType1.id) shouldBe userType1
                }
                expect("user not found") {
                    userService.findUserBy(userType2.id) shouldBe null
                }
            }
            context("createUser") {
                expect("create user") {
                    userService.createUser("sample1") shouldBe userType1
                }
            }
            context("updateUser") {
                expect("update user") {
                    userService.updateUser(UserInputType(userType2.id, "sample2")) shouldBe userType2
                }
            }
        }
        context("error") {
            context("findUserBy") {
                expect("invalid id") {
                    shouldThrowExactly<IllegalArgumentException> {
                        userService.findUserBy(ID(""))
                    }
                }
            }
            context("createUser") {
                expect("invalid name") {
                    shouldThrowExactly<IllegalArgumentException> {
                        userService.createUser(name = "")
                    }
                }
                expect("invalid iconUrl") {
                    shouldThrowExactly<IllegalArgumentException> {
                        userService.createUser("test", "a".repeat(2001))
                    }
                }
            }
            context("updateUser") {
                expect("invalid id") {
                    shouldThrowExactly<IllegalArgumentException> {
                        userService.updateUser(UserInputType(ID(""), "test"))
                    }
                }
                expect("invalid name") {
                    shouldThrowExactly<IllegalArgumentException> {
                        userService.updateUser(UserInputType(ID(""), ""))
                    }
                }
                expect("invalid iconUrl") {
                    shouldThrowExactly<IllegalArgumentException> {
                        userService.updateUser(UserInputType(ID(""), "test", "a".repeat(2001)))
                    }
                }
            }
        }
    }
}