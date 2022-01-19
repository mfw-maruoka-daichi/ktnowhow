package com.moneyforward.ktnowhow.service

import com.moneyforward.ktnowhow.model.Tag
import com.moneyforward.ktnowhow.repository.TagRepository
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk

class TagServiceImplTest : DescribeSpec() {

    private val tagNotFoundRepository = mockk<TagRepository> {
        every { getAll() } returns emptyList()
    }
    private val tagNotFoundService = TagServiceImpl(tagNotFoundRepository)

    private val tagRepository = mockk<TagRepository> {
        every { getAll() } returns listOf(
            Tag(id = 1L, name = "tag1"),
            Tag(id = 2L, name = "tag2"),
            Tag(id = 3L, name = "tag3")
        )
    }
    private val tagService = TagServiceImpl(tagRepository)

    init {
        describe("tag") {
            it("not exists") {
                tagNotFoundService.getAllTags().shouldBeEmpty()
            }
            it("exists") {
                tagService.getAllTags().size shouldBe 3
            }
        }
    }
}