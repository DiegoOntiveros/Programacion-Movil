package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

// ─── Colores Constantes ───────────────────────────────────────────────────────
private val Purple       = Color(0xFF3D3AA8)
private val White        = Color(0xFFFFFFFF)
private val TextDark     = Color(0xFF1A1A2E)
private val TextGray     = Color(0xFF8888AA)
private val LightGray    = Color(0xFFF5F5FA)
private val FacebookBlue = Color(0xFF3B5998)
private val GoogleRed    = Color(0xFFDB4437)
private val LinkedInBlue = Color(0xFF0077B5)

// ─── Activity Principal con Navegación ────────────────────────────────────────
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = "welcome") {
                composable("welcome") { WelcomeScreen(navController) }
                composable("login") { LoginScreen(navController) }
                composable("signup") { SignUpScreen(navController) }
            }
        }
    }
}

// ─── Pantalla de Bienvenida ───────────────────────────────────────────────────
@Composable
fun WelcomeScreen(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize().background(Purple)) {
        Column(modifier = Modifier.fillMaxSize()) {
            Box(modifier = Modifier.fillMaxWidth().weight(1f), contentAlignment = Alignment.Center) {
                DecorativeElements()
                PhoneWithPersonIllustration()
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(White, shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
                    .padding(horizontal = 32.dp, vertical = 40.dp)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("Hello", fontSize = 32.sp, fontWeight = FontWeight.ExtraBold, color = TextDark)
                    Spacer(modifier = Modifier.height(12.dp))
                    Text("Welcome To Little Drop, where\nyou manage you daily tasks",
                        fontSize = 14.sp, color = TextGray, textAlign = TextAlign.Center)

                    Spacer(modifier = Modifier.height(32.dp))

                    Button(
                        onClick = { navController.navigate("login") },
                        modifier = Modifier.fillMaxWidth().height(52.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Purple),
                        shape = RoundedCornerShape(50)
                    ) {
                        Text("Login", fontSize = 16.sp, color = White)
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedButton(
                        onClick = { navController.navigate("signup") },
                        modifier = Modifier.fillMaxWidth().height(52.dp),
                        border = androidx.compose.foundation.BorderStroke(2.dp, Purple),
                        shape = RoundedCornerShape(50)
                    ) {
                        Text("Sign Up", fontSize = 16.sp, color = Purple)
                    }

                    Spacer(modifier = Modifier.height(28.dp))
                    Text("Sign up using", fontSize = 13.sp, color = TextGray)
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                        SocialCircle(FacebookBlue, "f")
                        SocialCircle(GoogleRed, "G")
                        SocialCircle(LinkedInBlue, "in")
                    }
                }
            }
        }
    }
}

// ─── Pantalla de Login ────────────────────────────────────────────────────────
@OptIn(ExperimentalMaterial3Api::class)
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
            onClick = { /* Acción futura */ },
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

// ─── Pantalla de Registro ─────────────────────────────────────────────────────
@Composable
fun SignUpScreen(navController: NavController) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().background(White).padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Create Account", fontSize = 28.sp, fontWeight = FontWeight.Bold, color = TextDark,
            modifier = Modifier.padding(top = 40.dp))

        Spacer(modifier = Modifier.height(48.dp))

        CustomTextField(value = name, onValueChange = { name = it }, label = "Full Name")
        Spacer(modifier = Modifier.height(16.dp))
        CustomTextField(value = email, onValueChange = { email = it }, label = "Email")
        Spacer(modifier = Modifier.height(16.dp))
        CustomTextField(value = pass, onValueChange = { pass = it }, label = "Password", isPassword = true)

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { /* Acción futura */ },
            modifier = Modifier.fillMaxWidth().height(52.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Purple),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Create Account")
        }

        Spacer(modifier = Modifier.weight(1f))
        Text("Already have an account? Login",
            modifier = Modifier.clickable { navController.popBackStack() }, color = Purple)
    }
}

// ─── Componente TextField Modernizado (Sin errores) ──────────────────────────
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(value: String, onValueChange: (String) -> Unit, label: String, isPassword: Boolean = false) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        visualTransformation = if (isPassword) PasswordVisualTransformation() else androidx.compose.ui.text.input.VisualTransformation.None,
        // Solución al error 'unresolved reference'
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Purple,
            unfocusedBorderColor = LightGray,
            focusedContainerColor = LightGray,
            unfocusedContainerColor = LightGray,
            focusedLabelColor = Purple,
            unfocusedLabelColor = TextGray
        )
    )
}

// ─── Elementos Visuales Auxiliares ───────────────────────────────────────────
@Composable
private fun SocialCircle(color: Color, label: String) {
    Box(modifier = Modifier.size(40.dp).background(color, CircleShape), contentAlignment = Alignment.Center) {
        Text(text = label, color = White, fontSize = 13.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
private fun DecorativeElements() {
    Box(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.size(8.dp).align(Alignment.TopCenter).offset(y = 24.dp).background(White, CircleShape))
        Box(modifier = Modifier.size(20.dp).align(Alignment.TopEnd).offset(x = (-32).dp, y = 40.dp).border(2.dp, White.copy(alpha = 0.6f), CircleShape))
        Text("+", color = White.copy(alpha = 0.5f), fontSize = 20.sp, modifier = Modifier.align(Alignment.TopStart).offset(x = 28.dp, y = 80.dp))
    }
}

@Composable
private fun PhoneWithPersonIllustration() {
    Box(modifier = Modifier.size(220.dp), contentAlignment = Alignment.Center) {
        Box(modifier = Modifier.width(130.dp).height(200.dp).background(Color(0xFF1C1C3A), RoundedCornerShape(24.dp)).border(3.dp, Color(0xFF2E2E5E), RoundedCornerShape(24.dp)))
        PersonFigure(modifier = Modifier.align(Alignment.CenterEnd).offset(x = (-4).dp, y = 10.dp))
    }
}

@Composable
private fun PersonFigure(modifier: Modifier = Modifier) {
    val skinColor  = Color(0xFFFFCBA4)
    Column(modifier = modifier.width(50.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Box(modifier = Modifier.size(26.dp).background(skinColor, CircleShape))
        Spacer(modifier = Modifier.height(2.dp))
        Box(modifier = Modifier.width(32.dp).height(40.dp).background(Purple, RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)))
    }
}