package com.ilya.examen.viewmodels


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ilya.examen.data.StoreDarkMode
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ThemeViewModel @Inject constructor(
    private val storeDarkMode: StoreDarkMode // Hilt lo inyecta
) : ViewModel() {

    // Expone el estado del tema como un StateFlow
    val isDarkMode: StateFlow<Boolean> = storeDarkMode.getDarkMode
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = false
        )

    // Función para guardar el tema (¡Uso de Corrutina!)
    fun saveDarkMode(value: Boolean) {
        viewModelScope.launch { // <--- CORRUTINA
            storeDarkMode.saveDarkMode(value)
        }
    }
}