package com.moneyforward.ktnowhow.service

import com.expediagroup.graphql.generator.scalars.ID
import com.moneyforward.ktnowhow.graphql.type.UserInputType
import com.moneyforward.ktnowhow.graphql.type.UserType
import com.moneyforward.ktnowhow.model.User
import com.moneyforward.ktnowhow.model.UserInput
import com.moneyforward.ktnowhow.repository.UserRepository
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.ExpectSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk

class UserServiceImplTest : ExpectSpec() {

    private val userType1 = UserType(1L, "sample1", null)
    private val user1 = with(userType1) { User(rawId, name, iconUrl) }
    private val userInput1 = UserInput(userType1.rawId, "updatedName", null)
    private val updatedUser1 = with(userInput1) { User(id, name!!, iconUrl) }
    private val updatedUserType1 = with(updatedUser1) { UserType(id, name, iconUrl) }
    private val userType2 = UserType(2L, "not found", null)
    private val userInput2 = UserInput(userType2.rawId, userType2.name, userType2.iconUrl)

    private val userRepository = mockk<UserRepository> {
        every { findUserBy(userType1.rawId) } returns user1
        every { findUserBy(userType2.rawId) } returns null
        every { createUser(any()) } returns user1
        every { updateUser(userInput1) } returns updatedUser1
        every { updateUser(userInput2) } returns null
        every { deleteUser(userType1.rawId) } returns userType1.rawId
        every { deleteUser(userType2.rawId) } returns null
    }
    private val userService = UserServiceImpl(userRepository)

    init {
        context("normal") {
            context("findUserBy") {
                expect("user found") {
                    userService.findUserById(userType1.id) shouldBe userType1
                }
                expect("user not found") {
                    userService.findUserById(userType2.id) shouldBe null
                }
            }
            context("createUser") {
                expect("create user") {
                    userService.createUser("sample1") shouldBe userType1
                }
            }
            context("updateUser") {
                expect("update user") {
                    userService.updateUser(
                        UserInputType(userType1.id, userInput1.name, userInput1.iconUrl)
                    ) shouldBe updatedUserType1
                }
            }
            context("deleteUser") {
                expect("delete user") {
                    userService.deleteUser(userType1.id) shouldBe userType1.id
                }
            }
        }
        context("error") {
            context("findUserBy") {
                expect("invalid id") {
                    shouldThrowExactly<IllegalArgumentException> {
                        userService.findUserById(ID(""))
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
                        userService.updateUser(UserInputType(ID(""), userInput1.name, userInput1.iconUrl))
                    }
                }
                expect("invalid name") {
                    shouldThrowExactly<IllegalArgumentException> {
                        userService.updateUser(UserInputType(userType1.id, "", userInput1.iconUrl))
                    }
                }
                expect("invalid iconUrl") {
                    shouldThrowExactly<IllegalArgumentException> {
                        userService.updateUser(UserInputType(userType1.id, userInput1.name, "a".repeat(2001)))
                    }
                }
                expect("user not found") {
                    shouldThrowExactly<IllegalStateException> {
                        userService.updateUser(UserInputType(userType2.id, userInput2.name, userInput2.iconUrl))
                    }
                }
            }
            context("deleteUser") {
                expect("invalid id") {
                    shouldThrowExactly<IllegalArgumentException> {
                        userService.deleteUser(ID(""))
                    }
                }
                expect("user not found") {
                    shouldThrowExactly<IllegalStateException> {
                        userService.deleteUser(userType2.id)
                    }
                }
            }
        }
    }
}