package com.danielflores.localroomapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.danielflores.localroomapp.viewModels.ChronometerViewModel
import com.danielflores.localroomapp.viewModels.CronosViewModel
import com.danielflores.localroomapp.views.AddView
import com.danielflores.localroomapp.views.EditView
import com.danielflores.localroomapp.views.HomeView

/***
 * Project: LocalRoomApp
 * Package: com.danielflores.localroomapp.navigation
 * Created by Kevin Daniel Flores Nataren
 * File created at 10/October/2025 at 10:19
 * All rights reserved 2025.
 **/

@Composable
fun NavManager(cronoViewModel: ChronometerViewModel, cronosViewModel: CronosViewModel){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Home"){
        composable ("Home"){
            HomeView(navController, cronosViewModel)
        }
        composable("AddView"){
            AddView(navController, cronoViewModel, cronosViewModel)
        }
        composable("EditView/{id}", arguments = listOf(
            navArgument("id"){type = NavType.LongType}
        )){
            val id = it.arguments?.getLong("id") ?: 0
            EditView(navController, id,  cronoViewModel, cronosViewModel)
        }
    }
}