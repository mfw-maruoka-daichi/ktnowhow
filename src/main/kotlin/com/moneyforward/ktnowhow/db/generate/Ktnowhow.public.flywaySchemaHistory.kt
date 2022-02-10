import java.time.LocalDateTime
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.`java-time`.datetime

object FlywaySchemaHistory : Table("flyway_schema_history") {
    val installedRank: Column<Int> = integer("installed_rank")

    val version: Column<String?> = varchar("version", 50).nullable()

    val description: Column<String> = varchar("description", 200)

    val type: Column<String> = varchar("type", 20)

    val script: Column<String> = varchar("script", 1000)

    val checksum: Column<Int?> = integer("checksum").nullable()

    val installedBy: Column<String> = varchar("installed_by", 100)

    val installedOn: Column<LocalDateTime> = datetime("installed_on")

    val executionTime: Column<Int> = integer("execution_time")

    val success: Column<Boolean> = bool("success").index("flyway_schema_history_s_idx")

    override val primaryKey: PrimaryKey = PrimaryKey(installedRank)
}
