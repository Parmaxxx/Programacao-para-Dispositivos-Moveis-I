package com.example.petshop.model

import java.time.LocalDate

data class Pet(var nome : String = "",
    var raca : String = "",
    var peso : Float = 0.0F,
    var nascimento : LocalDate = LocalDate.now())
