package com.danielflores.localroomapp.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.danielflores.localroomapp.components.CenterAppBar
import com.danielflores.localroomapp.components.CronCard
import com.danielflores.localroomapp.shared.FloatButton
import com.danielflores.localroomapp.viewModels.CronosViewModel
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox

/***
 * Project: LocalRoomApp
 * Package: com.danielflores.localroomapp.views
 * Created by Kevin Daniel Flores Nataren
 * File created at 10/October/2025 at 10:17
 * All rights reserved 2025.
 **/

@Composable
fun HomeView(navController: NavController, cronosViewModel: CronosViewModel){
    Scaffold(
        topBar = {
            CenterAppBar(
                name = "Cronos App",
                containerColor = Color.Transparent,
            )
        },
        floatingActionButton = {
            FloatButton {
                navController.navigate("AddView")
            }
        }
    ) {
        ContentHomeView(it, navController, cronosViewModel)
    }
}

@Composable
fun ContentHomeView(
    paddingValues: PaddingValues,
    navController: NavController,
    cronosViewModel: CronosViewModel
){
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .padding(horizontal = 5.dp)
            .fillMaxSize(),
    ) {
        val cronosList by cronosViewModel.cronosList.collectAsState()
        LazyColumn {
            items(cronosList) {
                val delete = SwipeAction(
                    icon = rememberVectorPainter(Icons.Default.Delete),
                    background = Color.Red,
                    onSwipe = { cronosViewModel.deleteCrono(it) },
                )
                SwipeableActionsBox(
                    endActions = listOf(delete),
                    swipeThreshold = 200.dp
                ) {
                    CronCard (
                        it.title,
                        it.crono
                    ) {
                        navController.navigate("EditView/${it.id}")
                    }
                }
            }
        }
    }
}