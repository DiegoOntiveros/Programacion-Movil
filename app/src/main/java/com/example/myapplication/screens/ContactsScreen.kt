package com.example.myapplication.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactsScreen(navController: NavController) {
    // Colors based on your design
    val bluePrimary = Color(0xFF0D6EFD)
    val lightGrayBg = Color(0xFFF8F9FA)
    val textDark = Color(0xFF212529)

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("My Contacts", fontWeight = FontWeight.Bold, color = textDark) },
                navigationIcon = {
                    IconButton(onClick = { /* Menu action */ }) {
                        Icon(Icons.Default.Menu, contentDescription = "Menu")
                    }
                },
                actions = {
                    IconButton(onClick = { /* Profile action */ }) {
                        Icon(Icons.Default.AccountCircle, contentDescription = "Profile")
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color.White)
            )
        },
        bottomBar = {
            NavigationBar(containerColor = Color.White) {
                NavigationBarItem(
                    selected = true,
                    onClick = { },
                    icon = { Icon(Icons.Default.AccountBox, contentDescription = "Contacts") },
                    label = { Text("Contacts") },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = bluePrimary,
                        selectedTextColor = bluePrimary,
                        indicatorColor = Color.Transparent
                    )
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { /* Solo lógica aquí */ },
                    icon = { Icon(Icons.Default.List, contentDescription = "Recent") }, // ¡Con llaves!
                    label = { Text("Recent") } // ¡Con llaves!
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { },
                    icon = { Icon(Icons.Default.Star, contentDescription = "Favorites") },
                    label = { Text("Favorites") }
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color.White)
        ) {
            // --- FORM SECTION ---
            Column(modifier = Modifier.padding(24.dp)) {
                Text("Name", fontWeight = FontWeight.SemiBold, fontSize = 14.sp, color = Color.DarkGray)
                Spacer(modifier = Modifier.height(4.dp))
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    placeholder = { Text("e.g. John Doe", color = Color.Gray) },
                    leadingIcon = { Icon(Icons.Default.Person, contentDescription = null, tint = Color.Gray) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = Color.LightGray
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text("Phone", fontWeight = FontWeight.SemiBold, fontSize = 14.sp, color = Color.DarkGray)
                Spacer(modifier = Modifier.height(4.dp))
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    placeholder = { Text("+1 234 567 8900", color = Color.Gray) },
                    leadingIcon = { Icon(Icons.Default.Phone, contentDescription = null, tint = Color.Gray) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = Color.LightGray
                    )
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Add Button
                Button(
                    onClick = { /* Add action */ },
                    modifier = Modifier.fillMaxWidth().height(48.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = bluePrimary),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Icon(Icons.Default.Add, contentDescription = null, tint = Color.White)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Add Contact", color = Color.White, fontWeight = FontWeight.Bold)
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Clear Button
                Button(
                    onClick = { /* Clear action */ },
                    modifier = Modifier.fillMaxWidth().height(48.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = lightGrayBg),
                    shape = RoundedCornerShape(12.dp),
                    elevation = ButtonDefaults.buttonElevation(0.dp)
                ) {
                    Icon(Icons.Default.Clear, contentDescription = null, tint = textDark)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Clear Fields", color = textDark, fontWeight = FontWeight.Bold)
                }
            }

            // --- LIST HEADER ---
            Box(modifier = Modifier.fillMaxWidth().background(lightGrayBg).padding(horizontal = 24.dp, vertical = 8.dp)) {
                Text("CONTACT LIST", color = Color.Gray, fontSize = 12.sp, fontWeight = FontWeight.Bold)
            }

            // --- CONTACTS LIST ---
            LazyColumn {
                val contacts = listOf(
                    Triple("Ana Garcia", "+34 612 345 678", Color(0xFFE7F0FD) to Color(0xFF0D6EFD)),
                    Triple("Carlos Rodriguez", "+34 699 888 777", Color(0xFFE6F4EA) to Color(0xFF137333)),
                    Triple("Elena Martinez", "+34 655 443 322", Color(0xFFFCE8E6) to Color(0xFFC5221F))
                )

                items(contacts) { contact ->
                    ContactListItem(contact.first, contact.second, contact.third.first, contact.third.second)
                    HorizontalDivider(color = lightGrayBg, thickness = 1.dp)
                }
            }
        }
    }
}

@Composable
fun ContactListItem(name: String, phone: String, bgColor: Color, iconColor: Color) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier.size(48.dp).background(bgColor, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(Icons.Default.Person, contentDescription = null, tint = iconColor)
        }

        Spacer(modifier = Modifier.width(16.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(name, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color(0xFF212529))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Phone, contentDescription = null, tint = Color.Gray, modifier = Modifier.size(12.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text(phone, color = Color.Gray, fontSize = 13.sp)
            }
        }

        Icon(Icons.Default.KeyboardArrowRight, contentDescription = "View more", tint = Color.Gray)
    }
}