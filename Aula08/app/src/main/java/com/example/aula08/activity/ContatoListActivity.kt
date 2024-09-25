package com.example.aula08.activity

import android.app.Activity
import android.os.Bundle
import android.os.PersistableBundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aula08.R
import com.example.aula08.model.Contato
import com.example.aula08.recicleview.ContatoAdapter

class ContatoListActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.contato_lista_layout)

        val recyclerView = findViewById<RecyclerView>(R.id.rcv_contato_lista)
        val lista = ArrayList<Contato>()
        lista.add(Contato("0","feomfoewf@oewm","tonico","432648322"))
        val adapter = ContatoAdapter(this, lista)
        recyclerView.adapter = adapter

        recyclerView.layoutManager =LinearLayoutManager(this)



    }
}