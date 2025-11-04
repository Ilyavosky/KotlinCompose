package com.danielflores.localroomapp.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.danielflores.localroomapp.components.CenterAppBar
import com.danielflores.localroomapp.components.FormatTimeText
import com.danielflores.localroomapp.models.Cronos
import com.danielflores.localroomapp.shared.CircleButton
import com.danielflores.localroomapp.shared.MainTextField
import com.danielflores.localroomapp.viewModels.ChronometerViewModel
import com.danielflores.localroomapp.viewModels.CronosViewModel

/***
 * Project: LocalRoomApp
 * Package: com.danielflores.localroomapp.views
 * Created by Kevin Daniel Flores Nataren
 * File created at 10/October/2025 at 10:15
 * All rights reserved 2025.
 **/

@Composable
fun EditView(
    navController: NavController,
    id: Long,
    cronoViewModel: ChronometerViewModel,
    cronosViewModel: CronosViewModel
) {
    Scaffold (
        topBar = {
            CenterAppBar(
                name = "Edit Crono",
                containerColor = Color.Transparent,
            ) {
                navController.popBackStack()
            }
        },
    ) {
        ContentEditView(id, it, navController, cronoViewModel, cronosViewModel)
    }
}

@Composable
fun ContentEditView(
    id: Long,
    paddingValues: PaddingValues, navController: NavController,
    cronoViewModel: ChronometerViewModel,
    cronosViewModel: CronosViewModel
) {
    val state = cronoViewModel.state
    LaunchedEffect (state.activeChronometer) {
        cronoViewModel.cronos()
    }
    LaunchedEffect(Unit) {
        cronoViewModel.getCronoById(id)
    }
    Column (
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        FormatTimeText(cronoViewModel.cronoTime)
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(vertical = 16.dp)
        ) {
            CircleButton (
                icon = Icons.Default.PlayArrow,
                isEnabled = !state.activeChronometer,
            ) {
                cronoViewModel.initTimer()
            }
            CircleButton(
                icon = Icons.Default.Pause,
                isEnabled = state.activeChronometer,
            ) {
                cronoViewModel.pauseTimer()
            }
        }
        MainTextField(
            value = state.title,
            onValueChange = { cronoViewModel.onValue(it) },
            label = "Title"
        )
        Button (
            onClick = {
                cronosViewModel.updateCrono(
                    Cronos(
                        id = id,
                        title = state.title,
                        crono = cronoViewModel.cronoTime
                    )
                )
                cronoViewModel.stopTimer()
                navController.popBackStack()
            }
        ) {
            Text("Update")
        }
        DisposableEffect(Unit) {
            onDispose { cronoViewModel.stopTimer() }
        }
    }
}