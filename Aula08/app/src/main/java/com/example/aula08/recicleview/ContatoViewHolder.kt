package com.example.aula08.recicleview

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.aula08.R
import com.example.aula08.model.Contato

class ContatoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val txtNome: TextView = itemView.findViewById(R.id.txt_item_nome)
    private val txtTelefone: TextView = itemView.findViewById(R.id.txt_item_telefone)
    private val txtEmail: TextView = itemView.findViewById(R.id.txt_item_email)

    fun bind(contato: Contato) {
        txtNome.text = contato.nome
        txtTelefone.text = contato.telefone
        txtEmail.text = contato.email
    }
}
