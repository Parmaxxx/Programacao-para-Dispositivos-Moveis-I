package com.example.calculadorajetpackcompose

import android.os.Bundle
import androidx.compose.material3.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculadorajetpackcompose.ui.theme.CalculadoraJetPackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculadoraJetPackComposeTheme {
                    Calculadora()
                }
            }
        }
    }
    @Preview
    @Composable
    fun Calculadora() {
        val nomesBotoes = listOf(
            "1", "2", "3", "+",
            "4", "5", "6", "-",
            "7", "8", "9", "*",
            ".", "0", "=", "/"
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Row {
                OutlinedTextField(value = "", onValueChange = {},
                    placeholder = { Text("Numeros", fontSize = 12.sp) },
                modifier = Modifier.weight(1f)
                )
                Button(
                    onClick = {},
                    modifier = Modifier.weight(0.3f)
                ) {
                    Text("CE", fontSize = 28.sp)
                }
            }
            for (linha in 0 .. 3) {
                Row(
                    modifier = Modifier.fillMaxWidth().weight(1f),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    for (coluna in 0..3) {
                        Button(
                            onClick = {},
                            modifier = Modifier.weight(1f)
                        ) {
                            val indice = linha * 4 + coluna
                            val texto = nomesBotoes[indice]
                            Text(texto, fontSize = 28.sp)
                        }
                    }
                }
            }
        }
    }


