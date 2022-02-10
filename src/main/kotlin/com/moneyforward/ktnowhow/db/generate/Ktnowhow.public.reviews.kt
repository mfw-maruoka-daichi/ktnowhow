import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.Column

object Reviews : LongIdTable("reviews", "id") {
    val knowhow: Column<Long> =
        long("knowhow").references(Knowhows.id).index("fk_reviews_knowhow_id_index_6")

    val rate: Column<Int> = integer("rate")

    val comment: Column<String?> = varchar("comment", 140).nullable()

    val author: Column<Long> =
        long("author").references(Users.id).index("fk_reviews_author_id_index_6")
}
