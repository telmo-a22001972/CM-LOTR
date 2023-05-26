package pt.ulusofona.cm.lotrcharactersoffline.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import pt.ulusofona.cm.lotrcharactersoffline.dao.LOTRCharacterDao
import pt.ulusofona.cm.lotrcharactersoffline.model.LOTRCharacterRoom

// Este array deverá conter todas as entidades do modelo de dados
@Database(entities = [LOTRCharacterRoom::class], version = 1)
abstract class LOTRCharacterDatabase : RoomDatabase() {

    abstract fun characterDao(): LOTRCharacterDao

    companion object {
        private var instance: LOTRCharacterDatabase? = null

        fun getInstance(context: Context): LOTRCharacterDatabase {
            synchronized(this) {
                if(instance == null) {
                    instance = Room.databaseBuilder(
                        context,
                        LOTRCharacterDatabase::class.java,
                        "lotr_character_database"
                    )
                    .fallbackToDestructiveMigration()
                    .build()
                }
                return instance as LOTRCharacterDatabase
            }
        }
    }
}