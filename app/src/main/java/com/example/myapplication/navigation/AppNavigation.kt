package com.example.myapplication.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

// Tus imports de las pantallas
import com.example.myapplication.screens.WelcomeScreen
import com.example.myapplication.screens.LoginScreen
import com.example.myapplication.screens.SignUpScreen
import com.example.myapplication.screens.ContactsScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "welcome"
    ) {
        // Pantalla de Bienvenida
        composable("welcome") {
            WelcomeScreen(navController)
        }

        // Pantalla de Inicio de Sesión
        composable("login") {
            LoginScreen(navController)
        }

        // Pantalla de Registro
        composable("signup") {
            SignUpScreen(navController)
        }

        composable("contacts") {
            ContactsScreen(navController)
        }
    }
}