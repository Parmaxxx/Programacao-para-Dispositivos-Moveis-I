package com.example.aula_06

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View.OnCreateContextMenuListener
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
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


class AgendaContatoActivity : Activity() {
    val URL_BASE = "https://aula-mobile-1bc01-default-rtdb.firebaseio.com"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        setContentView(R.layout.agenda_layout)

        val edtNome = findViewById<EditText>(R.id.edtNome)
        val edtFone = findViewById<EditText>(R.id.edtFone)
        val edtEmail = findViewById<EditText>(R.id.edtEmail)
        val btnGravar = findViewById<Button>(R.id.btnGravar)
        val btnPesquisar = findViewById<Button>(R.id.btnPesquisar)
        var client = OkHttpClient()

        val gson = Gson()
        btnGravar.setOnClickListener{
            val contato = Contato(
                nome = edtNome.text.toString(),
                email = edtEmail.text.toString(),
                telefone = edtFone.text.toString()
            )
            val contatoJson = gson.toJson(contato)
            Log.d("AGENDA", "JSON criado : $contatoJson")

            val request = Request.Builder()
                .url("$URL_BASE/contatos.json")
                .post( contatoJson.toRequestBody("application/json".toMediaType()))
                .build()

            val response = object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    Log.v("AGENDA","Erro: ${e.message} ao cadastrar o contato")
                    runOnUiThread {
                        Toast.makeText(
                            this@AgendaContatoActivity,
                            "Erro: ${e.message} ao cadastrar o contato\"",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }

                override fun onResponse(call: Call, response: Response) {
                    Log.v("AGENDA", "Contato cadastrado com sucesso!")
                    runOnUiThread {
                    Toast.makeText(
                        this@AgendaContatoActivity,
                        "Contato Cadastrado com sucesso!",
                        Toast.LENGTH_LONG
                    ).show()}
                }
            }
            client.newCall(request).enqueue(response)
        }

    btnPesquisar.setOnClickListener{
        val resquest = Request.Builder()
            .url("$URL_BASE/contatos.json")
            .get().build()

        val response = object : Callback{
            override fun onFailure(call: Call, e: IOException) {
                runOnUiThread {
                    Toast.makeText(
                        this@AgendaContatoActivity,
                        "Erro: ${e.message} ao cadastrar o contato\"",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

            override fun onResponse(call: Call, response: Response) {
                val contatosJson = response.body?.string()
                Log.d("AGENDA", "Respostas : $contatosJson")
                val tipo = object : TypeToken<HashMap<String?, Contato?>>() {}.type
                val contatos : HashMap<String?,Contato?> = gson.fromJson(contatosJson, tipo)
                var contatoEncontrado = false
                for(contato in contatos.values){
                    Log.d("AGENDA", "Contatos : $contato")
                    runOnUiThread{
                    if (contato != null && contato.nome.contains(edtNome.text)){
                            edtNome.setText(contato.nome)
                            edtFone.setText(contato.telefone)
                            edtEmail.setText(contato.email)
                        contatoEncontrado = true
                        }
                        }
                    }
                    }




        }
        client.newCall(resquest).enqueue(response)
    }

    }
}