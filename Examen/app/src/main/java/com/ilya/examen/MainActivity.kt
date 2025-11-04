package com.ilya.examen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import com.ilya.examen.navigation.NavManager
import com.ilya.examen.ui.theme.ExamenTheme
import com.ilya.examen.viewmodels.ThemeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    // Pedimos el ViewModel del Tema aquí para que controle el tema de TODA la app
    private val themeViewModel: ThemeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // Observamos el estado del tema
            val isDarkMode by themeViewModel.isDarkMode.collectAsState()

            // Pasamos el estado al tema principal
            ExamenTheme(darkTheme = isDarkMode) {
                // El NavManager se encarga de todo lo demás
                NavManager()
            }
        }
    }
}