package com.enjoy.imccalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.enjoy.imccalculator.ui.theme.IMCCalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IMCCalculatorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Container()
                }
            }
        }
    }
}

private fun handleIMC(weight: Double, height: Double): String {
    val result = weight / (height * height)
    when {
        result < 18.5 -> {
            return "Você está abaixo do peso"
        }
        result > 18.5 && result < 24.9 -> {
            return "Seu peso está normal"
        }
        result > 25 && result < 29.9 -> {
            return "Você está está com sobrepeso"
        }
        result > 30 && result < 34.9 -> {
            return "Você está está com Obesidade Grau I"
        }
        result > 35 && result < 39.9 -> {
            return "Você está com Obesidade Grau II"
        }
        result > 40 -> {
            return "Você está com Obesidade Mórbida"
        }
        else -> return "Algo deu errado"
    }
    return "${result}"

}

@Composable
fun Container() {
    var peso by remember {
        mutableStateOf("")
    }

    var altura by remember {
        mutableStateOf("")
    }

    var resultado by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .padding(24.dp)
            .fillMaxSize()
    ) {
        Text(
            text = "Saiba agora se você está no seu peso ideal", style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                color = Color.DarkGray
            ),
            modifier = Modifier.padding(0.dp, 32.dp)
        )

        OutlinedTextField(
            value = peso,
            onValueChange = {
                peso = it
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            label = { Text(text = "Peso") },
            placeholder = { Text(text = "Digite seu peso") },
            modifier = Modifier
                .fillMaxWidth()
        )

        OutlinedTextField(
            value = altura,
            onValueChange = {
                altura = it
            },
            label = { Text(text = "Altura") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            placeholder = { Text(text = "Digite seu peso") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 16.dp)
        )

        Button(
            onClick = { resultado = handleIMC(peso.toDouble(), altura.toDouble()/100) },
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp)
        ) {
            Text(text = "Calcular")
        }

        
        if (resultado.isNotEmpty()){
            Text(text = resultado, style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                color = Color.DarkGray
            ),
                modifier = Modifier.padding(0.dp, 32.dp)
            )
        }


    }
}