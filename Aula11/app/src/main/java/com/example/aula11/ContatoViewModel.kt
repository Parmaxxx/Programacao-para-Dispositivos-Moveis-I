package com.example.aula11

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class ContatoViewModel : ViewModel() {
    val lista = mutableListOf<Contato>()
    val nome =mutableStateOf("")
    val email = mutableStateOf("")
    val telefone =  mutableStateOf("")

    fun gravar(){
        val contato = Contato(nome=nome.value,
            telefone=telefone.value,
            email= email.value)
        lista.add(contato)
    }
}