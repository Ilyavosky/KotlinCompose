package com.danielflores.localroomapp.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danielflores.localroomapp.models.Cronos
import com.danielflores.localroomapp.repositories.CronosRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/***
 * Project: LocalRoomApp
 * Package: com.danielflores.localroomapp.viewModels
 * Created by Kevin Daniel Flores Nataren
 * File created at 10/October/2025 at 10:02
 * All rights reserved 2025.
 **/
@HiltViewModel
class CronosViewModel @Inject constructor(private val repository: CronosRepository): ViewModel() {
    var cronosList: MutableStateFlow<List<Cronos>> = MutableStateFlow(emptyList())
        private set

    init {
        viewModelScope.launch (Dispatchers.IO) {
            repository.getAllCronos().collect {
                if (it.isEmpty()) {
                    cronosList.value = emptyList()
                    return@collect
                }
                cronosList.value = it
            }
        }
    }

    fun getById(id: Long) = viewModelScope.launch {
        repository.getCronoById(id)
    }

    fun addCrono(cronos: Cronos) = viewModelScope.launch {
        repository.insert(cronos)
    }

    fun updateCrono(cronos: Cronos) = viewModelScope.launch {
        repository.update(cronos)
    }

    fun deleteCrono(cronos: Cronos) = viewModelScope.launch {
        repository.delete(cronos)
    }
}