package com.moneyforward.ktnowhow.repository

import com.moneyforward.ktnowhow.db.H2TestDatabase
import com.moneyforward.ktnowhow.db.entity.TagEntity
import com.moneyforward.ktnowhow.db.table.Tags
import io.kotest.core.spec.Spec
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.shouldBe
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

class TagRepositoryImplTest : DescribeSpec() {

    // todo ProjectConfigか何かでやる
    override fun beforeSpec(spec: Spec) {
        H2TestDatabase.connect()
        transaction {
            SchemaUtils.create(Tags)
        }

        super.beforeSpec(spec)
    }

    private val tagRepository = TagRepositoryImpl()

    init {
        describe("tag") {
            it("not exists") {
                transaction {
                    tagRepository.getAll().shouldBeEmpty()
                }
            }
            it("exists") {
                transaction {
                    TagEntity.new { name = "tag1" }
                    TagEntity.new { name = "tag2" }
                    TagEntity.new { name = "tag3" }
                }
                transaction {
                    tagRepository.getAll().size shouldBe 3
                }
            }
        }
    }
}