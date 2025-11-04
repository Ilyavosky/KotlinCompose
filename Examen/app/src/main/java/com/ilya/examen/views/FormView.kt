package com.ilya.examen.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ilya.examen.viewmodels.FormViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormView(navController: NavController) {
    val viewModel: FormViewModel = hiltViewModel()

    var textValue by rememberSaveable { mutableStateOf("") }

    val savedData by viewModel.allFormData.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Vista 3: Formulario (Room)") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Volver")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = textValue,
                onValueChange = { textValue = it },
                label = { Text("Dato para guardar en Room") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                viewModel.saveData(textValue)
                textValue = ""
            }) {
                Text("Guardar Dato")
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text("Datos Guardados:", style = MaterialTheme.typography.titleMedium)

            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(savedData) { data ->
                    Text("ID: ${data.id}, Texto: ${data.text}", modifier = Modifier.padding(4.dp))
                }
            }
        }
    }
}