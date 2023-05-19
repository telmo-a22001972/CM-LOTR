package pt.ulusofona.cm.lotrcharactersoffline.data

import android.util.Log
import okhttp3.*
import org.json.JSONArray
import org.json.JSONObject
import pt.ulusofona.cm.lotrcharactersoffline.model.LOTR
import pt.ulusofona.cm.lotrcharactersoffline.model.LOTRCharacter
import java.io.IOException

class LOTROkHttp(
    private val baseUrl: String,
    private val apiKey: String,
    private val client: OkHttpClient
) : LOTR () {

    override fun getCharacters(onFinished: (Result<List<LOTRCharacter>>) -> Unit) {

        // Preparação do pedido, é necessário apiKey e do url
        val request: Request = Request.Builder()
            .url("$baseUrl/character")
            .addHeader("Authorization", "Bearer $apiKey")
            .build()

        // Execução do pedido, em caso de erro, o método onFailure é chamado(ex: timeout)
        // se tudo correr bem, teremos a resposta ao pedido no método onResponse
        client.newCall(request).enqueue(object : Callback {

            override fun onFailure(call: Call, e: IOException) {
                onFinished(Result.failure(e))
            }

            // Processar a respsota ao pedido
            override fun onResponse(call: Call, response: Response) {
                // Se a respsota devolver um erro, ex: 403 acesso negado ao web service
                if(!response.isSuccessful) {
                    onFinished(Result.failure(IOException("Unexpected code $response")))
                } else {
                    // Processamento da resposta
                    val body = response.body?.string()
                    if(body != null) {
                        // Guardamos a resposta num JSONObject
                        val jsonObject = JSONObject(body)

                        // Guardamos agora numa array os resultados do JSONObject
                        val jsonCharactersList = jsonObject["docs"] as JSONArray
                        val lotrCharacters = mutableListOf<LOTRCharacter>()

                        for (i in 0 until jsonCharactersList.length()) {
                            val jsonCharacter = jsonCharactersList[i] as JSONObject
                            lotrCharacters.add(
                                LOTRCharacter(
                                    jsonCharacter.getString("_id"),
                                    jsonCharacter.getString("birth"),
                                    jsonCharacter.getString("death"),
                                    // optString retorna "" se não encontrar o valor
                                    jsonCharacter.optString("gender"),
                                    jsonCharacter.getString("name"),
                                )
                            )
                        }
                        onFinished(Result.success(lotrCharacters))
                    }
                }
            }
        })
    }

    override fun insertCharacters(characters: List<LOTRCharacter>, onFinished: () -> Unit) {
        Log.e("APP", "web service is not able to insert characters")
    }

    override fun clearAllCharacters(onFinished: () -> Unit) {
        Log.e("APP", "web service is not able to clear all characters")
    }


}