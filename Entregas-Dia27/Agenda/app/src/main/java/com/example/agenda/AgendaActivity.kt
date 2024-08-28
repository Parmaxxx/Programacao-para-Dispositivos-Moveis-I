package com.example.agenda

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class AgendaActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.agenda_layout)

        var edtNome = findViewById<EditText>(R.id.edtNome)
        var edtFone = findViewById<EditText>(R.id.edtFone)
        var edtEmail = findViewById<EditText>(R.id.edtEmail)
        var btnGravar = findViewById<Button>(R.id.btnGravar)
        var btnPesquisar = findViewById<Button>(R.id.btnPesquisar)

         btnGravar.setOnClickListener{
             var nome = edtNome.text.toString()
             var fone = edtFone.text.toString()
             var email = edtEmail.text.toString()

             Log.v("Agenda","Nome: {nome}" +
                     "\nTelefone : {fone}" +
                     "\nEmail : {email}")
         }
    }
    }
