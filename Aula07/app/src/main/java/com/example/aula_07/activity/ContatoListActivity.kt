package com.example.aula_07.activity

import android.app.Activity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aula_06.R
import com.example.aula_07.model.Contato
import com.example.aula_07.recycleView.ContatoAdapter

class ContatoListActivity : Activity() {
    val lista = ArrayList<Contato>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.contato_lista_activity)
        lista.add(Contato("Joao","#24324324","rfiehgiw"))
        lista.add(Contato("Maria","#324324324","rfieewfewfewfhgiw"))
        lista.add(Contato("Tonico","#2443232324324","324324324rfewf"))

        val adapter = ContatoAdapter(this, lista)
        val recycler=findViewById<RecyclerView>(R.id.rcv_contato_lista)
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(this)

    }
}