package com.moneyforward.ktnowhow.service

import com.expediagroup.graphql.generator.scalars.ID
import com.moneyforward.ktnowhow.graphql.type.UserPropertyType
import com.moneyforward.ktnowhow.graphql.type.UserType
import com.moneyforward.ktnowhow.model.DefinedUser
import com.moneyforward.ktnowhow.model.UndefinedUser
import com.moneyforward.ktnowhow.repository.UserRepository
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.ExpectSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk

class UserServiceImplTest : ExpectSpec() {

    private val userProperty1 = UserPropertyType("sample1", null)
    private val userType1 = UserType(1L, userProperty1)
    private val user1 = with(userType1) { DefinedUser(rawId, property.name, property.iconUrl) }

    private val invalidNameProperty = UserPropertyType("", null)
    private val invalidIconUrlProperty = UserPropertyType("test", "a".repeat(2001))

    private val updateProperty = UserPropertyType("updatedName", "aaa")
    private val updatedUser1 = DefinedUser(userType1.rawId, updateProperty.name, updateProperty.iconUrl)
    private val updatedUserType1 = with(updatedUser1) { UserType(rawId, updateProperty) }

    private val userProperty404 = UserPropertyType("not found", null)
    private val userType404 = UserType(404L, userProperty404)

    private val userRepository = mockk<UserRepository> {
        every { findUserBy(userType1.rawId) } returns user1
        every { findUserBy(userType404.rawId) } returns null
        every { upsertUser(UndefinedUser(userProperty1.name, userProperty1.iconUrl)) } returns user1
        every {
            upsertUser(
                DefinedUser(updatedUser1.rawId, updateProperty.name, updateProperty.iconUrl)
            )
        } returns updatedUser1
        every {
            upsertUser(DefinedUser(userType404.rawId, userProperty404.name, userProperty404.iconUrl))
        } throws IllegalStateException()
        every { deleteUser(userType1.rawId) } returns userType1.rawId
        every { deleteUser(userType404.rawId) } returns null
    }
    private val userService = UserServiceImpl(userRepository)

    init {
        context("normal") {
            context("findUserBy") {
                expect("user found") {
                    userService.findUserById(userType1.id) shouldBe userType1
                }
                expect("user not found") {
                    userService.findUserById(userType404.id) shouldBe null
                }
            }
            context("createUser") {
                expect("create user") {
                    userService.createUser(userProperty1) shouldBe userType1
                }
            }
            context("updateUser") {
                expect("update user") {
                    userService.updateUser(updatedUserType1.id, updateProperty) shouldBe updatedUserType1
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
                        userService.createUser(invalidNameProperty)
                    }
                }
                expect("invalid iconUrl") {
                    shouldThrowExactly<IllegalArgumentException> {
                        userService.createUser(invalidIconUrlProperty)
                    }
                }
            }
            context("updateUser") {
                expect("invalid id") {
                    shouldThrowExactly<IllegalArgumentException> {
                        userService.updateUser(ID(""), updateProperty)
                    }
                }
                expect("invalid name") {
                    shouldThrowExactly<IllegalArgumentException> {
                        userService.updateUser(userType1.id, invalidNameProperty)
                    }
                }
                expect("invalid iconUrl") {
                    shouldThrowExactly<IllegalArgumentException> {
                        userService.updateUser(userType1.id, invalidIconUrlProperty)
                    }
                }
                expect("user not found") {
                    shouldThrowExactly<IllegalStateException> {
                        userService.updateUser(userType404.id, userProperty404)
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
                        userService.deleteUser(userType404.id)
                    }
                }
            }
        }
    }
}