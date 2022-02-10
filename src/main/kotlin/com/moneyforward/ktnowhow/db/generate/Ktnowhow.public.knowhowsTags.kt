import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

object KnowhowsTags : Table("knowhows_tags") {
    val id: Column<Long> = long("id").autoIncrement()

    val knowhowId: Column<Long> =
        long("knowhow_id").references(Knowhows.id).uniqueIndex("fk_knowhows_tags_knowhow_id_id_index_7")
            .uniqueIndex("idx_knowhow_id")

    val tagId: Column<Long> =
        long("tag_id").references(Tags.id).uniqueIndex("fk_knowhows_tags_tag_id_id_index_7").uniqueIndex("idx_tag_id")

    override val primaryKey: PrimaryKey = PrimaryKey(knowhowId, tagId)

    init {
    }
}
