import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.Column

object Users : LongIdTable("users", "id") {
    val name: Column<String> = varchar("name", 30)

    val iconUrl: Column<String?> = varchar("icon_url", 2000).nullable()
}
