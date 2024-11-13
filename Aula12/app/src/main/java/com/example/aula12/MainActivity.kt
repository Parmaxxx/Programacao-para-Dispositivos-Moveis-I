package com.example.aula12

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aula12.ui.theme.Aula12Theme

class MainActivity : ComponentActivity() {
    val viewModel by viewModels<PetViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Aula12Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PetSpaceScreen(modifier = Modifier.padding(innerPadding),
                        viewModel = viewModel)
                }
            }
        }
    }
}

@Composable
fun PetSpaceScreen(modifier: Modifier = Modifier, viewModel: PetViewModel) {
    val context = LocalContext.current
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF0D0D1E)) // Fundo escuro para o estilo futurista
    ) {
        // Imagem do pet astronauta no topo
        Image(
            painter = painterResource(id = R.drawable.pet),
            contentDescription = "Pet Space",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(Color(0xFF13132B))
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(200.dp)) // Espaço para a imagem no topo

            Text(
                text = "Pet Space",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF00FFD1) // Cor neon para título
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Campos de entrada com cores futuristas e bordas arredondadas
            CustomTextField(
                label = "Nome do Pet",
                value = viewModel.nome.value,
                onValueChange = { viewModel.nome.value = it }
            )
            CustomTextField(
                label = "Raça",
                value = viewModel.raca.value,
                onValueChange = { viewModel.raca.value = it }
            )
            CustomTextField(
                label = "Peso",
                value = viewModel.peso.value.toString(),
                onValueChange = { viewModel.peso.value = it.toFloatOrNull() ?: 0f }
            )
            CustomTextField(
                label = "Idade",
                value = viewModel.idade.value.toString(),
                onValueChange = { viewModel.idade.value = it.toIntOrNull() ?: 0 }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = { viewModel.gravar()
                        Toast.makeText(
                            context,
                            "Pet Salvo com sucesso!",
                            Toast.LENGTH_SHORT
                        ).show()},
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00FFD1)),
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("Gravar", color = Color.Black)
                }

                Button(
                    onClick = { viewModel.lerTodos() },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6200EA)),
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("Pesquisar", color = Color.White)
                }
                Box(
                    modifier = Modifier.fillMaxSize(), // Ocupa o tamanho total da tela
                    contentAlignment = Alignment.Center // Centraliza o conteúdo
                ) {
                    LazyColumn(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally // Centraliza horizontalmente cada item
                    ) {
                        item {
                            Text(text = "Pets")
                        }
                        items(items = viewModel.pets) { pet ->
                            Card(modifier = Modifier.padding(8.dp)) { // Adiciona um padding para espaçamento entre os cards
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(8.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally // Centraliza o conteúdo dentro do Card
                                ) {
                                    Text(text = pet.nome)
                                    Text(text = pet.raca)
                                    Text(text = pet.peso.toString())
                                    Text(text = pet.idade.toString())
                                }
                            }
                        }
                    }
                }
            }}}}




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(label: String, onValueChange: (String) -> Unit, value: String) {
    TextField(
        value =value,
        onValueChange = onValueChange,
        label = { Text(label, color = Color(0xFFAAAAAA)) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        shape = RoundedCornerShape(12.dp),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color(0xFF1E1E2E),
            focusedIndicatorColor = Color(0xFF00FFD1),
            unfocusedIndicatorColor = Color(0xFF444466),

        )
    )
}


