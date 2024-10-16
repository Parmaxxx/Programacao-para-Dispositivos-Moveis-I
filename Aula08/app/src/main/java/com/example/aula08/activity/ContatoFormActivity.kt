package com.example.aula08.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.aula08.R
import com.example.aula08.model.Contato

class ContatoFormActivity : Activity() {
    val lista =ArrayList<Contato>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.contato_form_activity_layout)

        val edtNome = findViewById<TextView>(R.id.edtNome)
        val edtEmail = findViewById<TextView>(R.id.edtEmail)
        val edtTelefone = findViewById<TextView>(R.id.edtFone)

        val btnGravar = findViewById<Button>(R.id.btnGravar)
        val btnListar = findViewById<Button>(R.id.btnListar)

        btnGravar.setOnClickListener{
            val c1 = Contato(id = "0",
                nome = edtNome.text.toString(),
                telefone = edtTelefone.text.toString(),
                email = edtEmail.text.toString()
            )
            try {
                lista.add(c1) // Adiciona o contato à lista
                runOnUiThread {
                    Toast.makeText(
                        this@ContatoFormActivity,
                        "Contato salvo com sucesso!",
                        Toast.LENGTH_LONG
                    ).show()
                }
            } catch (e: IllegalArgumentException) {
                e.printStackTrace()
                runOnUiThread {
                    Toast.makeText(
                        this@ContatoFormActivity,
                        "Erro ao salvar contato!",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

        }

        btnListar.setOnClickListener{
            val intent = Intent(this,ContatoListActivity::class.java)
            val b1 = Bundle()
            b1.putSerializable("Lista",lista)
            intent.putExtras(b1)
            startActivity(intent)
        }
    }
}