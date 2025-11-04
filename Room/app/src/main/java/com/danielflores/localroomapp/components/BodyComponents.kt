package com.danielflores.localroomapp.components

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/***
 * Project: LocalRoomApp
 * Package: com.danielflores.localroomapp.components
 * Created by Kevin Daniel Flores Nataren
 * File created at 10/October/2025 at 10:08
 * All rights reserved 2025.
 **/

@SuppressLint("DefaultLocale")
@Composable
fun FormatTimeText(time: Long){
    val seconds = (time / 1000) % 60
    val minutes = (time / (1000 * 60)) % 60
    val hours = (time / (1000 * 60 * 60)) % 24
    val formattedTime = String.format("%02d:%02d:%02d", hours, minutes, seconds)
    // Display the formatted time
    Text(
        formattedTime,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun CronCard(
    title: String,
    crono: Long,
    onClick: () -> Unit,
){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable{onClick()}
    ){
        Column {
            Text(title, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Row {
                Icon(Icons.Default.Timer, contentDescription = "Timer", tint = Color.Gray)
                FormatTimeText(crono)
            }
            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(),
                thickness = 1.dp,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}