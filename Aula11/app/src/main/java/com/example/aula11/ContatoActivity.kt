package com.example.aula11

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.ui.platform.LocalContext
import com.example.aula11.ui.theme.Aula11Theme




class ContatoActivity : ComponentActivity() {

    val viewModel : ContatoViewModel by viewModels<ContatoViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            Aula11Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TelaPrincipal(
                        titulo = "Agenda de Contato",
                        viewModel= viewModel,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun TelaPrincipal(
            titulo: String,
            modifier: Modifier = Modifier,
            viewModel: ContatoViewModel
        ) {
            val context = LocalContext.current

    Column(modifier=Modifier.fillMaxSize()) {
        Text(
            text = titulo,
            modifier = modifier
        )
        Row(
            modifier=Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Nome:")
            OutlinedTextField(value = viewModel.nome.value,
                onValueChange = { valor ->
                    viewModel.nome.value = valor.lowercase() })
        }
        Row(
            modifier=Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Email:")
            OutlinedTextField(value = viewModel.email.value, onValueChange = {
                viewModel.email.value = it
            })
        }
        Row(
            modifier=Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Telefone:")
            OutlinedTextField(value = viewModel.telefone.value, onValueChange = {
                viewModel.telefone.value = it
            })
        }
        Row(modifier=Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround) {
            Button(onClick={ viewModel.gravar()
                Toast.makeText(
                    context,
                    "Contato Gravado com sucesso!",
                    Toast.LENGTH_SHORT
                ).show()
            }) {
                Text("Gravar")
            }
            Button(onClick={viewModel.carregarTodos()}) {
                Text("Pesquisar")
            }
        }

        LazyColumn{
            item {
                Text("Inicio da Lazy Column")
            }
            items(items=viewModel.lista) { contato ->
                Card{
                    Text(contato.nome)
                    Text(contato.telefone)
                }
            }
            item {
                Text("Termino da Lazy Column")
            }
        }
    }
}

