package com.example.agenda

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class AgendaContatoDetalhesActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detalhe_layout)

        val btnVoltar = findViewById<Button>(R.id.btn_voltar)
        val txtNome = findViewById<TextView>(R.id.txt_contato_nome)
        val nome = intent.extras?.getString("Nome")

        txtNome.text = nome

        btnVoltar.setOnClickListener {
            val intent = Intent(this,AgendaActivity::class.java)
            startActivity(intent)
        }
    }
}