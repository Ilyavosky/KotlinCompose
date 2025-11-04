package com.ilya.examen.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ilya.examen.data.FormDao
import com.ilya.examen.data.FormData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FormViewModel @Inject constructor(
    private val formDao: FormDao // Hilt inyecta el DAO
) : ViewModel() {

    // Expone la lista de datos guardados como un StateFlow
    val allFormData: StateFlow<List<FormData>> = formDao.getAllData()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    // Función para guardar datos (¡Uso de Corrutina!)
    fun saveData(text: String) {
        viewModelScope.launch { // <--- CORRUTINA
            if (text.isNotBlank()) {
                formDao.insert(FormData(text = text))
            }
        }
    }
}