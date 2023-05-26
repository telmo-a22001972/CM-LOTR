package pt.ulusofona.cm.lotrcharactersoffline.data

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okio.IOException
import pt.ulusofona.cm.lotrcharactersoffline.dao.LOTRCharacterDao
import pt.ulusofona.cm.lotrcharactersoffline.model.LOTR
import pt.ulusofona.cm.lotrcharactersoffline.model.LOTRCharacter
import pt.ulusofona.cm.lotrcharactersoffline.model.LOTRCharacterRoom

class LOTRRoom(private val dao: LOTRCharacterDao) : LOTR() {

    override fun getCharacters(onFinished: (Result<List<LOTRCharacter>>) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val characters = dao.getAll().map {
                LOTRCharacter(
                    id = it.id,
                    birth = it.birth,
                    death = it.death,
                    gender = it.gender,
                    name = it.name
                )
            }
            onFinished(Result.success(characters))
        }

    }

    override fun insertCharacters(characters: List<LOTRCharacter>, onFinished: () -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            characters.map {
                LOTRCharacterRoom(
                    id = it.id,
                    birth = it.birth,
                    death = it.death,
                    gender = it.gender,
                    name = it.name
                )
            }.forEach{
                dao.insert(it)
                Log.i("LOTRRoom", "Inserted character ${it.name}")
            }
            onFinished()
        }
    }

    override fun clearAllCharacters(onFinished: () -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            dao.deleteAll()
            onFinished()
        }
    }
}