package com.example.aula05

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import java.io.IOException

data class Contato (
    var nome : String = "",
    var fone : String = "",
    var email : String = ""
)

class AgendaActivity : Activity() {
    var lista = ArrayList<Contato>()
    val URL_BASE = "https://aula-mobile-1bc01-default-rtdb.firebaseio.com"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.agenda_layout)

        var edtNome = findViewById<EditText>(R.id.edtNome)
        var edtFone =findViewById<EditText>(R.id.edtFone)
        var edtEmail = findViewById<EditText>(R.id.edtEmail)
        var btnGravar = findViewById<Button>(R.id.btnGravar)
        var btnPesquisar = findViewById<Button>(R.id.btnPesquisar)
        var client = OkHttpClient()


        btnPesquisar.setOnClickListener {
            for(contato in lista) {
                if (contato.nome.contains( edtNome.text)){
                    edtNome.setText(contato.nome)
                    edtFone.setText(contato.fone)
                    edtEmail.setText(contato.email)
                    break
                }
            }
        }

        btnGravar.setOnClickListener {
            val contatoJson =
            """
                {
                "nome":"${edtNome.text}",
                "telefone":"${edtFone.text},
                "email":"${edtEmail.text}"
                }
            """.trimIndent()

            val request = Request.Builder()
                .url("$URL_BASE/contatos.json")
                .post( contatoJson.toRequestBody("application/json".toMediaType()))
                .build()

            val response = object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    Log.v("AGENDA", "Erro: ${e.message} o cadastrar o contato")
                }

                override fun onResponse(call: Call, response: Response) {
                    Log.v("AGENDA","Contato cadastrado com sucesso")
                }
            }
            client.newCall( request ).enqueue( response )



        }

    }



}