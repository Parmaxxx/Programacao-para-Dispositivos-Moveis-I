package com.example.pets_lista

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pets_lista.ui.theme.PetsListaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PetsListaTheme {
                Formulario()
                }
            }
        }
    }

@Preview(showBackground = true)
@Composable
fun Formulario() {
    var nome by remember { mutableStateOf("") }
    var raca by remember { mutableStateOf("") }
    var peso by remember { mutableFloatStateOf(0.0f) }
    var idade by remember { mutableStateOf(0u) }
    var petsLista = remember {  mutableListOf<Pets>()  }
    val context = LocalContext.current

    Column (modifier = Modifier
        .padding(30.dp)
        .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Row {
            Text(text = "Pet Space", fontSize = 50.sp)
        }
        Spacer(modifier = Modifier.padding(20.dp))
        Row ( modifier=Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Nome : ",
                fontSize = 20.sp,
                modifier = Modifier.width(80.dp)
                )
            OutlinedTextField(
                value = nome,
                onValueChange = {nome = it},
                label = {Text("Digite o nome do seu Pet")}
            )
        }
        Row ( modifier=Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Raça : ",
                fontSize = 20.sp,
                modifier = Modifier.width(80.dp)
            )
            OutlinedTextField(
                value = raca,
                onValueChange = {raca = it},
                label = {Text("Digite a raça do seu Pet")}
            )
        }
        Row ( modifier=Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Peso : ",
                fontSize = 20.sp,
                modifier = Modifier.width(80.dp)
            )
            OutlinedTextField(
                value = peso.toString(),
                onValueChange = {pesoString -> peso = pesoString.toFloat()},
                label = {Text("Digite o peso do seu Pet")}
            )
        }
        Row ( modifier=Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Idade : ",
                fontSize = 20.sp,
                modifier = Modifier.width(80.dp)
            )
            OutlinedTextField(
                value = idade.toString(),
                onValueChange = {idadeString -> idade = idadeString.toUInt()},
                label = {Text("Digite a idade do seu Pet")}
            )
        }
        Spacer(modifier = Modifier.padding(150.dp))
        Row (
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.Bottom){
            Button(onClick = {
                if (nome.isNotEmpty() && raca.isNotEmpty() && peso > 0f && idade > 0u) {
                    val pet = Pets(
                        nome = nome,
                        raca = raca,
                        peso = peso,
                        idade = idade
                    )
                    petsLista.add(pet)
                    Toast.makeText(
                        context,
                        "Pet Gravado com sucesso!",
                        Toast.LENGTH_SHORT
                    ).show()

                    nome = ""
                    raca = ""
                    peso = 0.0f
                    idade = 0u
                }
            }) {Text(text = "Gravar")
            }
            Button(onClick = {
                petsLista.forEach { pets ->
                    if(pets.nome == nome){
                        raca = pets.raca
                        peso = pets.peso
                        idade = pets.idade
                    }
                    else{
                        Toast.makeText(
                            context,
                            "Nenhum Pet Encontrado",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                }
                }

            ) {Text(text = "Pesquisar")
        }
    }

}}

