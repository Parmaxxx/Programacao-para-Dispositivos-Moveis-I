package com.example.aula08.recicleview

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.aula08.R
import com.example.aula08.model.Contato


class ContatoAdapter(val contexto : Context,
                     val lista : ArrayList<Contato>)
    : Adapter<ContatoViewHolder>() {

        val inflater = LayoutInflater.from(contexto)


    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ContatoViewHolder {
      val view = inflater.inflate(R.layout.contato_item_layout,
           parent,
           false)

        return ContatoViewHolder(view)
    }

    override fun getItemCount(): Int {
       return lista.size
    }

    override fun onBindViewHolder(holder: ContatoViewHolder, position: Int) {
        val contato = lista[position]
        holder.txtNome.text = contato.nome
        holder.txtEmail.text = contato.email
        holder.txtTelefone.text = contato.telefone
    }

}