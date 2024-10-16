package com.example.aula08.recicleview

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.aula08.R
import com.example.aula08.model.Contato

class ContatoAdapter(private val context: Context, private val lista: ArrayList<Contato>) : RecyclerView.Adapter<ContatoViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContatoViewHolder {
        val view = inflater.inflate(R.layout.contato_item_layout, parent, false)
        return ContatoViewHolder(view)
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(holder: ContatoViewHolder, position: Int) {
        val contato = lista[position]
        holder.bind(contato) // Usando o método bind do ViewHolder
    }
}
