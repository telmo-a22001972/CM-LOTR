package pt.ulusofona.cm.lotrcharactersoffline.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import pt.ulusofona.cm.lotrcharactersoffline.model.LOTRCharacterRoom

@Dao
interface LOTRCharacterDao {

    @Insert
    fun insert(character: LOTRCharacterRoom)

    @Insert
    fun insertAll(characters: List<LOTRCharacterRoom>)

    @Query("SELECT * FROM character ORDER BY name ASC")
    fun getAll(): List<LOTRCharacterRoom>

    @Query("SELECT * FROM character WHERE uuid = :uuid")
    fun getById(uuid: String): LOTRCharacterRoom

    @Query("DELETE FROM character")
    fun deleteAll()
}