package com.example.aula10

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.* // Importa classes para layout
import androidx.compose.material3.*
import androidx.compose.runtime.* // Importa estado do Jetpack Compose
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview

// Definição da data class para Contato
data class Contato(
    val nome: String = "",
    val telefone: String = "",
    val email: String = ""
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ContactApp()
        }
    }
}

@Composable
fun ContactApp() {
    // Definindo estados para os campos
    var nome by remember { mutableStateOf("") }
    var telefone by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    // Definindo uma lista de contatos
    val contactList = remember { mutableStateListOf<Contato>() }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Metade da tela superior com o formulário de contato
        Column(
            modifier = Modifier
                .fillMaxHeight(0.5f), // Ocupa metade da tela
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Agenda de Contatos", style = MaterialTheme.typography.headlineMedium)

            Spacer(modifier = Modifier.height(16.dp))

            // Campos de entrada (Nome, Telefone, Email)
            OutlinedTextField(
                value = nome,
                onValueChange = { nome = it },
                label = { Text("Nome") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = telefone,
                onValueChange = { telefone = it },
                label = { Text("Telefone") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Botões de Gravar e Pesquisar
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(onClick = {
                    if (nome.isNotEmpty() && telefone.isNotEmpty() && email.isNotEmpty()) {
                        // Adiciona o contato usando a data class Contato
                        contactList.add(Contato(nome, telefone, email))
                        Toast.makeText(
                            context,
                            "Contato Gravado com sucesso!",
                            Toast.LENGTH_SHORT
                        ).show()
                        // Limpa os campos após gravar
                        nome = ""
                        telefone = ""
                        email = ""
                    } else {
                        Toast.makeText(
                            context,
                            "Por favor, preencha todos os campos!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }) {
                    Text(text = "Gravar")
                }

                Button(onClick = { /* Lógica para pesquisar */ }) {
                    Text(text = "Pesquisar")
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Metade inferior para exibir a lista de contatos
        Column(
            modifier = Modifier
                .fillMaxHeight() // Ocupa o restante da tela
        ) {
            Text(text = "Contatos", style = MaterialTheme.typography.headlineSmall)

            Spacer(modifier = Modifier.height(8.dp))

            // Exibe a lista de contatos
            contactList.forEach { contact ->
                // Exibe os dados do contato formatados
                Card(modifier = Modifier.padding(8.dp)){
                    Text(contact.nome)
                    Text(contact.telefone)
                    Text(contact.email)

                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ContactApp()
}
