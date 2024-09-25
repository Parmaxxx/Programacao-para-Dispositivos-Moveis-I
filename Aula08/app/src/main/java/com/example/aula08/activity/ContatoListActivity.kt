package com.example.aula08.activity

import android.app.Activity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aula08.R
import com.example.aula08.model.Contato
import com.example.aula08.recicleview.ContatoAdapter

class ContatoListActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.contato_lista_layout)

        // Recupera as extras da Intent
        val extras = intent.extras
        val lista: ArrayList<Contato> = if (extras != null) {
            extras.getSerializable("lista") as? ArrayList<Contato> ?: ArrayList()
        } else {
            ArrayList() // Retorna uma lista vazia se não houver extras
        }

        // Configura o adapter e o RecyclerView
        val adapter = ContatoAdapter(this, lista)
        val recyclerView = findViewById<RecyclerView>(R.id.rcv_contato_lista)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}
