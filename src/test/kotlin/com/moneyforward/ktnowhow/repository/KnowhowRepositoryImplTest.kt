package com.moneyforward.ktnowhow.repository

import com.moneyforward.ktnowhow.db.H2TestDatabase
import com.moneyforward.ktnowhow.db.table.Knowhows
import com.moneyforward.ktnowhow.db.table.KnowhowsTags
import com.moneyforward.ktnowhow.db.table.Tags
import com.moneyforward.ktnowhow.db.table.Users
import io.kotest.core.spec.Spec
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

class KnowhowRepositoryImplTest : DescribeSpec() {

    // todo ProjectConfigか何かでやる
    override fun beforeSpec(spec: Spec) {
        H2TestDatabase.connect()
        transaction {
            SchemaUtils.create(Users)
            SchemaUtils.create(Tags)
            SchemaUtils.create(Knowhows)
            SchemaUtils.create(KnowhowsTags)
        }

        super.beforeSpec(spec)
    }

    private val userRepository = UserRepositoryImpl()
    private val tagRepository = TagRepositoryImpl()
    private val knowhowRepository = KnowhowRepositoryImpl()

    init {
        describe("add knowhow") {
            it("success") {
                val author = transaction { userRepository.createUser("user1") }

                transaction {
                    knowhowRepository.addKnowhow(
                        "Knowhow sample",
                        "dummy URL",
                        author.id,
                        null
                    ).title shouldBe "Knowhow sample"
                }
            }
        }
    }
}