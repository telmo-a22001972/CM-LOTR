package pt.ulusofona.cm.lotrcharactersoffline.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.UUID

@Entity(tableName = "character")
data class LOTRCharacterRoom(
    @PrimaryKey val uuid: String = UUID.randomUUID().toString(),
    val id: String,
    val birth: String,
    val death: String,
    val gender: String?,  // gender is optional
    val name: String
)