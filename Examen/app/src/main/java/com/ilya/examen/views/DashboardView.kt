package com.ilya.examen.views

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardView(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Examen Práctico (Dashboard)") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = { navController.navigate("theme_view") }) {
                Text("Ir a Vista 2: Cambiar Tema (DataStore)")
            }
            Spacer(modifier = Modifier.height(20.dp))
            Button(onClick = { navController.navigate("form_view") }) {
                Text("Ir a Vista 3: Guardar Datos (Room)")
            }
        }
    }
}