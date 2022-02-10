import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.Column

object Tags : LongIdTable("tags", "id") {
    val name: Column<String> = varchar("name", 30)
}
