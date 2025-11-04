package com.danielflores.localroomapp.states

/***
 * Project: LocalRoomApp
 * Package: com.danielflores.localroomapp.states
 * Created by Kevin Daniel Flores Nataren
 * File created at 10/October/2025 at 10:05
 * All rights reserved 2025.
 **/
data class CronoState(
    val activeChronometer: Boolean = false,
    val showSaveButton: Boolean = false,
    val showTextField: Boolean = false,
    val title: String = "",
)
