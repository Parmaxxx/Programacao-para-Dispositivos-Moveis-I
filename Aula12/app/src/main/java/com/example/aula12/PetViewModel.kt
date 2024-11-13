package com.example.aula12

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableFloatState
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import java.io.IOException

class PetViewModel : ViewModel(){
    var pets:MutableList<Pet> = mutableStateListOf()

    var nome:MutableState<String> = mutableStateOf("")
    var raca:MutableState<String> = mutableStateOf("")
    var peso: MutableFloatState = mutableFloatStateOf(0.0f)
    var idade:MutableIntState = mutableIntStateOf(0)

    val httpClient = OkHttpClient()
    val gson = Gson()
    val URL_BASE = "https://contato-android-10e12-default-rtdb.firebaseio.com"

    fun gravar(){
        val pet =Pet(
            nome= nome.value,
            raca = raca.value,
            peso = peso.value,
            idade= idade.value.toUInt()
        )

        val petJson = gson.toJson(pet)
        val request = Request.Builder()
            .post(petJson.toRequestBody("application/json".toMediaType()))
            .url("$URL_BASE/pets2.json")
            .build()

        val response = object :Callback{
            override fun onFailure(call: Call, e: IOException) {
                Log.e("PET","Erro ao gravar o PET")
            }

            override fun onResponse(call: Call, response: Response) {
                Log.d("PET", "Sucesso ao Gravar o PET\n$petJson")

            }
        }
        httpClient.newCall(request).enqueue(response)
    }

    fun lerTodos() {
        val request = Request.Builder()
            .url("$URL_BASE/pets.json")
            .get()
            .build()

        val response = object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("PET", "ERRO AO BUSCAR PETS", e)
            }

            override fun onResponse(call: Call, response: Response) {
                Log.i("PET", "SUCESSO AO BUSCAR OS PETS!")
                val body = response.body?.string() ?: "null"
                if (body != "null") {
                    val typeToken = object : TypeToken<HashMap<String?, Pet?>>() {}.type
                    val petsMap: HashMap<String?, Pet?> = gson.fromJson(body, typeToken)

                    // Atualize a lista de pets na thread principal
                    pets.clear()
                    petsMap.values.filterNotNull().forEach {
                        pets.add(it) // O Compose detectará essa mudança automaticamente
                    }
                }
            }
        }
        httpClient.newCall(request).enqueue(response)
    }

}
