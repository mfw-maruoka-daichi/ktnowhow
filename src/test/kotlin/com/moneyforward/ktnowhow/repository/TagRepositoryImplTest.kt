package com.moneyforward.ktnowhow.repository

import com.moneyforward.ktnowhow.db.H2TestDatabase
import com.moneyforward.ktnowhow.db.table.Tags
import io.kotest.core.spec.Spec
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.shouldBe
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

class TagRepositoryImplTest : DescribeSpec() {

    override fun beforeSpec(spec: Spec) {
        H2TestDatabase.connect() // todo ProjectConfigか何かでやる?
        transaction {
            SchemaUtils.create(Tags)
        }

        super.beforeSpec(spec)
    }

    override fun afterSpec(spec: Spec) {
        transaction {
            SchemaUtils.drop(Tags)
        }
        super.afterSpec(spec)
    }

    private val tagRepository = TagRepositoryImpl()

    init {
        describe("tag") {
            it("not exists") {
                transaction {
                    tagRepository.getAll().shouldBeEmpty()
                }
            }
            it("create one") {
                transaction {
                    tagRepository.createTag(name = "tag1").name shouldBe "tag1"
                }
            }
            it("create multiple") {
                transaction {
                    tagRepository.createTags(listOf("tag2", "tag3")).let {
                        it.size shouldBe 2
                        it.first().name shouldBe "tag2"
                        it.last().name shouldBe "tag3"
                    }
                }
            }
            it("exists") {
                transaction { tagRepository.getAll() }.size shouldBe 3
            }
        }
    }
}