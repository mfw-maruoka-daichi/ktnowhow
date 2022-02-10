import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.Column

object Knowhows : LongIdTable("knowhows", "id") {
    val title: Column<String> = varchar("title", 50)

    val url: Column<String> = varchar("url", 2000)

    val authorId: Column<Long> =
        long("author_id").references(Users.id).index("fk_knowhows_author_id_id_index_1").index("idx_author_id")
}
