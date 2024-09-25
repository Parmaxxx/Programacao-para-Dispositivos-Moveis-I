package com.example.aula08.activity

import android.app.Activity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.TextView
import com.example.aula08.R
import com.example.aula08.model.Contato

class ContatoFormActivity : Activity() {
    val lista =ArrayList<Contato>()
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.contato_form_activity_layout)

        val edtNome = findViewById<TextView>(R.id.edtNome)
        val edtEmail = findViewById<TextView>(R.id.edtEmail)
        val edtTelefone = findViewById<TextView>(R.id.edtFone)

        val btnGravar = findViewById<Button>(R.id.btnGravar)
        val btnListar = findViewById<Button>(R.id.btnListar)

        btnGravar.setOnClickListener{
            val c1 = Contato(id = "0",
                nome = edtNome.text.toString(),
                email = edtEmail.text.toString(),
                telefone = edtTelefone.text.toString()
            )
            lista.add(c1)
        }
    }
}