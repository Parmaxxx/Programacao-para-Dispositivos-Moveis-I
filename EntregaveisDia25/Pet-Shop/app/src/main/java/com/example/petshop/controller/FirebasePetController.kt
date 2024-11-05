package com.example.petshop.controller

import android.content.Context
import com.example.petshop.model.Pet
import com.example.petshop.repository.FirebasePetRepository

class FirebasePetController(private val context: Context) {

    private val firebasePetRepository = FirebasePetRepository(context)

    fun carregarPets(onResult: (List<Pet>) -> Unit) {
        firebasePetRepository.carregarFirebase { pets ->

            onResult(pets)
        }
    }

    fun salvarPet(pet: Pet, onComplete: (Boolean) -> Unit) {
        firebasePetRepository.salvarFirebase(pet) { sucesso ->
            onComplete(sucesso)
        }
    }
}
