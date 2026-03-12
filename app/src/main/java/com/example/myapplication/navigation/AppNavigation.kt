package com.example.myapplication.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
// Asegúrate de que estos imports coincidan con la ubicación de tus archivos
import com.example.myapplication.screens.WelcomeScreen
import com.example.myapplication.screens.LoginScreen
import com.example.myapplication.screens.SignUpScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    // El NavHost gestiona el cambio entre pantallas mediante "rutas" (strings)
    NavHost(
        navController = navController,
        startDestination = "welcome" // La pantalla que se ve al abrir la app
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
    }
}