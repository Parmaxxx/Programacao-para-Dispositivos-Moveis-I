package com.example.petshop.activity

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.petshop.R
import com.example.petshop.model.Pet
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import java.io.IOException
import java.lang.ref.WeakReference
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

class PetActivity : Activity() {

    // Constante com a URL do RTDB
    private val firebaseUrl = "https://petshop-b3617-default-rtdb.firebaseio.com/pets.json"

    // Variáveis de instância para os EditTexts e Buttons
    private lateinit var edtNome: EditText
    private lateinit var edtRaca: EditText
    private lateinit var edtPeso: EditText
    private lateinit var edtNascimento: EditText
    private lateinit var btnGravar: Button
    private lateinit var btnPesquisar: Button

    // Lista para guardar os pets registrados
    private val listaDePets = mutableListOf<Pet>()

    // Criação do cliente OkHttp
    private val client = OkHttpClient()
    private val gson = Gson()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pet_layout)

        // Recuperar os elementos do layout
        edtNome = findViewById(R.id.edtNome)
        edtRaca = findViewById(R.id.edtRaca)
        edtPeso = findViewById(R.id.edtPeso)
        edtNascimento = findViewById(R.id.edtNasc)
        btnGravar = findViewById(R.id.btnGravar)
        btnPesquisar = findViewById(R.id.btnPesquisar)

        // Programar os botões
        btnGravar.setOnClickListener {
            val pet = paraEntidade()
            pet?.let { salvarFirebase(it) }
        }

        btnPesquisar.setOnClickListener {
            pesquisar()
        }

        // Carregar os pets ao iniciar
        carregarFirebase()
    }

    // Gera um objeto do tipo Pet
    private fun paraEntidade(): Pet? {
        val nome = edtNome.text.toString()
        val raca = edtRaca.text.toString()
        val peso = edtPeso.text.toString().toFloatOrNull()
        val nascimentoStr = edtNascimento.text.toString() // User input in dd/MM/yyyy format

        if (nome.isBlank() || raca.isBlank() || peso == null || nascimentoStr.isBlank()) {
            Toast.makeText(this, "Preencha todos os campos corretamente.", Toast.LENGTH_SHORT).show()
            return null
        }

        // Convert dd/MM/yyyy to LocalDate
        val nascimento: LocalDate? = try {
            // Define the formatter for Brazilian date input (dd/MM/yyyy)
            val inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

            // Parse the user input string into a LocalDate using Brazilian format
            LocalDate.parse(nascimentoStr, inputFormatter)

        } catch (e: DateTimeParseException) {
            Toast.makeText(this, "Data de nascimento inválida.", Toast.LENGTH_SHORT).show()
            null
        }

        if (nascimento == null) {
            return null
        }

        // Now, convert LocalDate back to Brazilian format (dd/MM/yyyy) for saving
        val outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        val formattedDate = nascimento.format(outputFormatter)  // Format to dd/MM/yyyy

        // You can now use `formattedDate` in the Brazilian format when saving
        return Pet(nome, raca, peso, formattedDate)
    }

    // Popular cada EditText com os dados de um Pet
    private fun paraTela(p: Pet) {
        edtNome.setText(p.nome)
        edtRaca.setText(p.raca)
        edtPeso.setText(p.peso.toString())
        edtNascimento.setText(p.nascimento.toString())
    }

    // Carregar pets do RTDB
    private fun carregarFirebase() {
        val request = Request.Builder()
            .url(firebaseUrl)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
                runOnUiThread {
                    Toast.makeText(this@PetActivity, "Erro ao carregar os pets.", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!it.isSuccessful) throw IOException("Unexpected code $it")

                    val json = it.body?.string()

                    // Check if the JSON is empty or null
                    if (json.isNullOrEmpty()) {
                        runOnUiThread {
                            Toast.makeText(this@PetActivity, "Nenhum dado encontrado.", Toast.LENGTH_SHORT).show()
                        }
                        return
                    }

                    // Parse JSON as a Map<String, Pet>, as Firebase stores data in a key-value format
                    val petMapType = object : TypeToken<Map<String, Pet>>() {}.type
                    val petsMap: Map<String, Pet>? = gson.fromJson(json, petMapType)

                    // Ensure petsMap is not null before proceeding
                    if (petsMap == null) {
                        runOnUiThread {
                            Toast.makeText(this@PetActivity, "Erro ao carregar os dados.", Toast.LENGTH_SHORT).show()
                        }
                        return
                    }

                    // Clear and populate the list with the Pets
                    listaDePets.clear()
                    listaDePets.addAll(petsMap.values)

                    runOnUiThread {
                        Toast.makeText(this@PetActivity, "Pets carregados com sucesso!", Toast.LENGTH_SHORT).show()
                    }
                }
            }

        })
    }

    // Salvar um Pet no RTDB
    private fun salvarFirebase(p: Pet) {
        val json = gson.toJson(p)
        val requestBody = RequestBody.create("application/json; charset=utf-8".toMediaType(), json)




        val request = Request.Builder()
            .url(firebaseUrl)
            .post(requestBody)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
                runOnUiThread {
                    Toast.makeText(this@PetActivity, "Erro ao salvar o pet.", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!it.isSuccessful) throw IOException("Unexpected code $it")

                    runOnUiThread {
                        Toast.makeText(this@PetActivity, "Pet salvo com sucesso!", Toast.LENGTH_SHORT).show()
                        carregarFirebase() // Atualizar a lista após salvar
                    }
                }
            }
        })
    }

    // Pesquisar um Pet na lista
    private fun pesquisar() {
        val nomeBusca = edtNome.text.toString()
        val petEncontrado = listaDePets.find { it.nome.contains(nomeBusca, ignoreCase = true) }
        petEncontrado?.let { paraTela(it) } ?: run {
            Toast.makeText(this, "Pet não encontrado.", Toast.LENGTH_SHORT).show()
        }
    }
}
