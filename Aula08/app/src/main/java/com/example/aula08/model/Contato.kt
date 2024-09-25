package com.example.aula08.model

import java.io.Serializable

data class Contato(var id: String = "",
    var nome : String = "", var telefone : String = "",
    var email: String = ""
) : Serializable