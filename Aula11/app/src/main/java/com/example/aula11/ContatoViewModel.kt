package com.example.aula11

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
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

class ContatoViewModel : ViewModel() {
    val lista = mutableListOf<Contato>()
    val nome =mutableStateOf("")
    val email = mutableStateOf("")
    val telefone =  mutableStateOf("")

    val URL_BASE = "https://contato-android-10e12-default-rtdb.firebaseio.com"
    val gson = Gson()
    val httpClient = OkHttpClient()

    fun gravar(){
        val contato = Contato(nome=nome.value,
            telefone=telefone.value,
            email= email.value)
        val contatoJson = gson.toJson(contato)

        val request = Request.Builder()
            .url("$URL_BASE/contatos.json")
            .post(contatoJson.toRequestBody(
                "application/json".toMediaType()
            ))
            .build()

        val response = object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("AGENDA","ERRO AO GRAVAR O CONTATO ${e.message}",e)


            }

            override fun onResponse(call: Call, response: Response) {
                Log.i("AGENDA", "CONTATO GRAVADO COM SUCESSO!\n$contato")
            }

        }
        httpClient.newCall(request).enqueue(response)
    }

    fun carregarTodos(){
        val request = Request.Builder()
            .url("$URL_BASE/contatos.json")
            .get()
            .build()

        val response = object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("AGENDA","ERRO AO TRAZER OS CONTATOS",e)
            }

            override fun onResponse(call: Call, response: Response) {
            Log.d("AGENDA","SUCESSO AO BUSCAR OS CONTATOS!" )
                val corpo = response.body?.string() ?: "null"
                if (corpo != "null"){
                    val typeToken = object : TypeToken<HashMap<String?, Contato?>>(){}.type
                    val contatosMap : HashMap<String? , Contato?> = gson.fromJson(corpo, typeToken)
                    for (entry in contatosMap){
                        val contato = entry.value
                        if (contato !=null){
                            lista.add(contato)

                        }
                    }
                }
            }
        }
        httpClient.newCall(request).enqueue(response)
    }

}