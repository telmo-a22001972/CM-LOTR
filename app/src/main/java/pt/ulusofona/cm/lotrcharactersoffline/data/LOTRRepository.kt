package pt.ulusofona.cm.lotrcharactersoffline.data

import android.content.Context
import android.util.Log
import pt.ulusofona.cm.lotrcharactersoffline.model.LOTR
import pt.ulusofona.cm.lotrcharactersoffline.model.LOTRCharacter

class LOTRRepository(
    private val context: Context,
    private val local: LOTR,
    private val remote: LOTR
    ) : LOTR() {

    companion object {
        private var instance: LOTRRepository? = null

        // Temos de executar o init antes do getInstance
        fun init(local: LOTR, remote: LOTR, context: Context) {
            if(instance == null) {
                instance = LOTRRepository(context, local, remote)
            }
        }

        fun getInstance(): LOTRRepository {
            if(instance == null) {
                throw RuntimeException("LOTRRepository is being accessed before being initialized.")
            }
            return instance as LOTRRepository
        }
    }

    override fun getCharacters(onFinished: (Result<List<LOTRCharacter>>) -> Unit) {
        if(ConnectivityUtil.isOnline(context)) {
            // Se tenho acesso à Internet, vou buscar os registos ao web service
            // e atualizo a base de dados com os novos registos eliminando os
            // antigos, porque podem ter eliminado personagens do web service
            remote.getCharacters { result ->
                if(result.isSuccess) {
                    result.getOrNull()?.let { characters ->
                        // Se tiver personagens para apresentar entra aqui
                        Log.i("APP", "Got ${characters.size} characters from remote web service.")
                        // Retirar esta linha quando forem fazer o exercício 1 da ficha
                        onFinished(Result.success(characters))

                        local.clearAllCharacters {
                            Log.i("APP", "Clearing local database.")
                            local.insertCharacters(characters) {
                                Log.i("APP", "Inserting characters in local database.")
                                onFinished(Result.success(characters))
                            }
                        }
                    }
                } else {
                    Log.w("APP", "Error getting characters from server")
                    onFinished(result)
                }
            }

        } else {
            // Se não tenho acesso à Internet, vou buscar os registos à base de dados
            // local
            Log.i("APP", "APP is offline. Getting characters from local database.")
            local.getCharacters(onFinished)
        }
    }

    override fun insertCharacters(characters: List<LOTRCharacter>, onFinished: () -> Unit) {
        TODO("Not yet implemented")
    }

    override fun clearAllCharacters(onFinished: () -> Unit) {
        TODO("Not yet implemented")
    }


}