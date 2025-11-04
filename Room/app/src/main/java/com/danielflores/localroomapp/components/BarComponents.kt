package com.danielflores.localroomapp.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.danielflores.localroomapp.shared.MainIconButton

/***
 * Project: LocalRoomApp
 * Package: com.danielflores.localroomapp.components
 * Created by Kevin Daniel Flores Nataren
 * File created at 10/October/2025 at 10:07
 * All rights reserved 2025.
 **/

@Composable
fun TitleBar(
    name: String,
) {
    Text(name, fontSize = 25.sp, color = Color.Black)
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun CenterAppBar(
    name: String,
    containerColor: Color,
    onNavigationClick: (() -> Unit)? = null
){
    CenterAlignedTopAppBar(
        title = { TitleBar(name) },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = containerColor
        ),
        navigationIcon = {
            if (onNavigationClick != null) {
                MainIconButton(
                    onClick = onNavigationClick,
                    icon = Icons.AutoMirrored.Filled.ArrowBack,
                    tint = Color.Black
                )
            }
        }
    )
}