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

@Composable
fun LoginScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().background(White).padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Welcome Back", fontSize = 28.sp, fontWeight = FontWeight.Bold, color = TextDark,
            modifier = Modifier.padding(top = 40.dp))
        Text("Login to your account", fontSize = 14.sp, color = TextGray)

        Spacer(modifier = Modifier.height(48.dp))

        CustomTextField(value = email, onValueChange = { email = it }, label = "Email Address")
        Spacer(modifier = Modifier.height(16.dp))
        CustomTextField(value = pass, onValueChange = { pass = it }, label = "Password", isPassword = true)

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { /* Acción de login */ },
            modifier = Modifier.fillMaxWidth().height(52.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Purple),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Login")
        }

        Spacer(modifier = Modifier.weight(1f))
        Text("Don't have an account? Sign Up",
            modifier = Modifier.clickable { navController.navigate("signup") }, color = Purple)
    }
}