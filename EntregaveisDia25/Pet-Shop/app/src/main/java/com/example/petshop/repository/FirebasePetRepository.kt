package com.example.petshop.repository

import android.content.Context
import android.widget.Toast
import com.example.petshop.model.Pet
import com.google.firebase.database.*
import java.util.*

class FirebasePetRepository(private val context: Context) {

    private val database = FirebaseDatabase.getInstance()
    private val ref = database.getReference("pets")

    fun carregarFirebase(onResult: (List<Pet>) -> Unit) {
        val lista = ArrayList<Pet>()
        ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (petSnapshot in snapshot.children) {
                    val pet = petSnapshot.getValue(Pet::class.java)
                    pet?.let { lista.add(it) }
                }
                onResult(lista)
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, "Erro ao carregar pets: ${error.message}", Toast.LENGTH_LONG).show()
            }
        })
    }

    fun salvarFirebase(p: Pet, onComplete: (Boolean) -> Unit) {
        val newPetRef = ref.push()
        newPetRef.setValue(p).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(context, "Sucesso ao cadastrar seu Pet!", Toast.LENGTH_LONG).show()
                onComplete(true)
            } else {
                Toast.makeText(context, "Erro ao cadastrar o Pet: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                onComplete(false)
            }
        }
    }
}
