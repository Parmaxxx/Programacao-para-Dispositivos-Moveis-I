package com.example.agenda

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText

import android.widget.Toast

data class Contato (
    var nome : String = "",
    var fone : String = "",
    var email : String = ""
)

class AgendaActivity : Activity() {
    var lista = ArrayList<Contato>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.agenda_layout)


        var edtNome = findViewById<EditText>(R.id.edtNome)
        var edtFone = findViewById<EditText>(R.id.edtFone)
        var edtEmail = findViewById<EditText>(R.id.edtEmail)
        var btnGravar = findViewById<Button>(R.id.btnGravar)
        var btnPesquisar = findViewById<Button>(R.id.btnPesquisar)
        var btnDetalhes = findViewById<Button>(R.id.btn_detalhe)


         btnGravar.setOnClickListener{

             val contato = Contato(
                 nome = edtNome.text.toString(),
                 fone = edtFone.text.toString(),
                 email = edtEmail.text.toString()
             )
             lista.add(contato)

             Toast.makeText(this,"Contato gravado com sucesso", Toast.LENGTH_LONG).show()
             edtNome.setText("")
             edtFone.setText("")
             edtEmail.setText("")

//             var nome = edtNome.text.toString()
//             var fone = edtFone.text.toString()
//             var email = edtEmail.text.toString()
//             Log.v("Agenda","Nome: {nome}" +
//                     "\nTelefone : {fone}" +
//                     "\nEmail : {email}")
         }

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

        btnDetalhes.setOnClickListener {
            val intent = Intent(this,AgendaContatoDetalhesActivity::class.java)
            val bundleData = Bundle()
            bundleData.putString("Nome",edtNome.text.toString())
            intent.putExtras(bundleData)
            startActivity(intent)
        }
    }
    }

