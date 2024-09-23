package com.example.petshop.activity

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.petshop.R
import com.example.petshop.controller.FirebasePetController
import com.example.petshop.model.Pet
import java.time.LocalDate

class PetActivity : Activity() {

    private lateinit var edtNome: EditText
    private lateinit var edtRaca: EditText
    private lateinit var edtPeso: EditText
    private lateinit var edtNascimento: EditText
    private lateinit var btnGravar: Button
    private lateinit var btnPesquisar: Button

    private val petController by lazy { FirebasePetController(this@PetActivity) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pet_layout)

        edtNome = findViewById(R.id.edtNome)
        edtRaca = findViewById(R.id.edtRaca)
        edtPeso = findViewById(R.id.edtPeso)
        edtNascimento = findViewById(R.id.edtNasc)
        btnGravar = findViewById(R.id.btnGravar)
        btnPesquisar = findViewById(R.id.btnPesquisar)

        carregarFirebase()

        btnGravar.setOnClickListener {
            val pet = paraEntidade()
            salvarFirebase(pet)
        }
    }

    fun paraEntidade(): Pet {
        val nome = edtNome.text.toString()
        val raca = edtRaca.text.toString()
        val peso = edtPeso.text.toString().toFloatOrNull() ?: 0.0F
        val nascimento = LocalDate.parse(edtNascimento.text.toString())
        return Pet(nome, raca, peso, nascimento)
    }

    fun paraTela(p: Pet) {
        edtNome.setText(p.nome)
        edtRaca.setText(p.raca)
        edtPeso.setText(p.peso.toString())
        edtNascimento.setText(p.nascimento.toString())
    }


    fun carregarFirebase() {
        petController.carregarPets { listaDePets ->

        }
    }


    fun salvarFirebase(p: Pet) {
        petController.salvarPet(p) { sucesso ->
            if (sucesso) {
                carregarFirebase()
            }
        }
    }
}
