package com.example.myapplication.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication.ui.theme.*
import com.example.myapplication.screens.components.CustomTextField
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
@Composable
fun SignUpScreen(navController: NavController) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }
    var confirmPass by remember { mutableStateOf("") }


    // Email: Obliga a tener texto + @ + texto + . + algo (ej: usuario@gmail.com)
    val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[a-z]{2,}$".toRegex()
    val isEmailValid = email.matches(emailRegex)

    // Contraseñas: Deben ser iguales Y tener al menos 6 caracteres
    val isPassValid = pass.length >= 6
    val isConfirmValid = confirmPass == pass && confirmPass.isNotEmpty()

    // Teléfono: Exactamente 10 números
    val isPhoneValid = phone.length == 10

    // El botón solo se activa si TODO es true
    val isFormValid = name.isNotEmpty() && isEmailValid && isPhoneValid && isPassValid && isConfirmValid

    Column(
        modifier = Modifier.fillMaxSize().background(White).padding(24.dp).verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Create Account", fontSize = 28.sp, fontWeight = FontWeight.Bold, color = TextDark)
        Spacer(modifier = Modifier.height(32.dp))

        // NOMBRE: Si intentas escribir un número, el 'if' lo ignora y NO se escribe nada
        CustomTextField(
            value = name,
            onValueChange = { if (it.all { c -> c.isLetter() || c.isWhitespace() }) name = it },
            label = "Full Name"
        )

        Spacer(modifier = Modifier.height(16.dp))

        // EMAIL: Se pone rojo si no cumple con el formato @ y .com
        CustomTextField(
            value = email,
            onValueChange = { email = it },
            label = "Email Address",
            isError = email.isNotEmpty() && !isEmailValid
        )

        Spacer(modifier = Modifier.height(16.dp))

        // TELÉFONO: Solo deja escribir números y máximo 10
        CustomTextField(
            value = phone,
            onValueChange = { if (it.all { c -> c.isDigit() } && it.length <= 10) phone = it },
            label = "Phone (10 digits)",
            isError = phone.isNotEmpty() && !isPhoneValid
        )

        Spacer(modifier = Modifier.height(16.dp))

        // CONTRASEÑA
        CustomTextField(
            value = pass,
            onValueChange = { pass = it },
            label = "Password (min. 6)",
            isPassword = true,
            isError = pass.isNotEmpty() && !isPassValid
        )

        Spacer(modifier = Modifier.height(16.dp))

        // CONFIRMAR: Rojo si no es igual a la de arriba
        CustomTextField(
            value = confirmPass,
            onValueChange = { confirmPass = it },
            label = "Confirm Password",
            isPassword = true,
            isError = confirmPass.isNotEmpty() && !isConfirmValid
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { /* Tu acción de registro aquí */ },
            modifier = Modifier.fillMaxWidth().height(52.dp),
            enabled = isFormValid, // EL BOTÓN NO SE PUEDE TOCAR SI FALTA ALGO
            colors = ButtonDefaults.buttonColors(
                containerColor = Purple,
                disabledContainerColor = LightGray // Se pone gris si es inválido
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Create Account", color = if (isFormValid) White else TextGray)
        }
    }
}