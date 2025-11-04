package com.ilya.examen.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ilya.examen.views.DashboardView
import com.ilya.examen.views.FormView
import com.ilya.examen.views.ThemeView

@Composable
fun NavManager() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "dashboard") {

        // Ruta 1: Dashboard
        composable("dashboard") {
            DashboardView(navController = navController)
        }

        // Ruta 2: Vista de Tema
        composable("theme_view") {
            ThemeView(navController = navController)
        }

        // Ruta 3: Vista de Formulario
        composable("form_view") {
            FormView(navController = navController)
        }
    }
}