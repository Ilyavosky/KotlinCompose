package com.danielflores.localroomapp.viewModels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danielflores.localroomapp.repositories.CronosRepository
import com.danielflores.localroomapp.states.CronoState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

/***
 * Project: LocalRoomApp
 * Package: com.danielflores.localroomapp.viewModels
 * Created by Kevin Daniel Flores Nataren
 * File created at 10/October/2025 at 10:03
 * All rights reserved 2025.
 **/
@HiltViewModel
class ChronometerViewModel @Inject constructor(private val repository: CronosRepository) :
    ViewModel() {
    var state by mutableStateOf(CronoState())
        private set

    var cronoJob by mutableStateOf<Job?>(null)
        private set

    var cronoTime by mutableLongStateOf(0L)
        private set

    fun getCronoById(id:Long){
        viewModelScope.launch (Dispatchers.IO) {
            repository.getCronoById(id).collect {
                if(it != null){
                    cronoTime = it.crono
                    state = state.copy(
                        title = it.title
                    )
                }else{
                    Log.d("Error", "Crono object is null")
                }
            }
        }
    }

    fun onValue(value: String) {
        state = state.copy(
            title = value
        )
    }

    fun initTimer() {
        state = state.copy(
            activeChronometer = true,
        )
    }

    fun pauseTimer() {
        state = state.copy(
            activeChronometer = false,
            showSaveButton = true
        )
    }

    fun stopTimer() {
        cronoJob?.cancel()
        cronoTime = 0L
        state = state.copy(
            activeChronometer = false,
            showSaveButton = false,
            showTextField = false,
            title = ""
        )
        cronoTime = 0L
    }

    fun showTextField() {
        state = state.copy(
            showTextField = true
        )
    }

    fun cronos() {
        if (state.activeChronometer) {
            cronoJob?.cancel()
            cronoJob = viewModelScope.launch {
                while (true) {
                    delay(1000)
                    cronoTime += 1000
                }
            }
        } else {
            cronoJob?.cancel()
        }
    }
}