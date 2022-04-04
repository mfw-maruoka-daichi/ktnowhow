package com.moneyforward.ktnowhow.repository

import com.moneyforward.ktnowhow.db.H2TestDatabase
import com.moneyforward.ktnowhow.db.table.Users
import com.moneyforward.ktnowhow.model.DefinedUser
import com.moneyforward.ktnowhow.model.UndefinedUser
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.Spec
import io.kotest.core.spec.style.ExpectSpec
import io.kotest.matchers.shouldBe
import java.sql.SQLException
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

class UserRepositoryImplTest : ExpectSpec() {

    override fun beforeSpec(spec: Spec) {
        H2TestDatabase.connect() // todo ProjectConfigか何かでやる?
        transaction {
            SchemaUtils.create(Users)
        }

        super.beforeSpec(spec)
    }

    override fun afterSpec(spec: Spec) {
        transaction {
            SchemaUtils.drop(Users)
        }
        super.afterSpec(spec)
    }

    private val userRepository = UserRepositoryImpl()

    init {
        context("normal") {
            expect("create user") {
                transaction {
                    userRepository.upsertUser(UndefinedUser("sampleUser", null)) shouldBe
                        DefinedUser(1L, "sampleUser", null)
                }
            }
            expect("user found") {
                transaction {
                    userRepository.findUserBy(1L)?.id shouldBe 1L
                }
            }
            expect("update user") {
                transaction {
                    userRepository.upsertUser(
                        DefinedUser(
                            1L,
                            "testUser",
                            "https://1.bp.blogspot.com/-rICfj66reA8/YJRppE8IMRI/AAAAAAABdsI/fkiIy_6KHAYUYVtWP1wPrtU7h6_UKDgXQCNcBGAsYHQ/s751/drink_beer_bin.png"
                        )
                    ) shouldBe DefinedUser(
                        1L,
                        "testUser",
                        "https://1.bp.blogspot.com/-rICfj66reA8/YJRppE8IMRI/AAAAAAABdsI/fkiIy_6KHAYUYVtWP1wPrtU7h6_UKDgXQCNcBGAsYHQ/s751/drink_beer_bin.png"
                    )
                }
            }
            expect("user not found on update") {
                transaction {
                    userRepository.upsertUser(DefinedUser(2L, "", null)) shouldBe null
                }
            }
            expect("user not found on delete") {
                transaction {
                    userRepository.deleteUser(2L) shouldBe null
                }
            }
            expect("delete user") {
                transaction {
                    userRepository.deleteUser(1L) shouldBe 1L
                }
            }
            expect("user not found") {
                transaction {
                    userRepository.findUserBy(1L) shouldBe null
                }
            }
        }
        context("error") {
            expect("DB disconnected") {
                shouldThrow<SQLException> {
                    transaction {
                        with(connection) {
                            if (!isClosed) close()
                        }
                        shouldThrow<SQLException> {
                            userRepository.findUserBy(1L)
                        }
                    }
                }
            }
        }
    }
}