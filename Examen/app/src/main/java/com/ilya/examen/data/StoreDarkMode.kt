package com.ilya.examen.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// Creamos el DataStore
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("DarkMode")

class StoreDarkMode(private val context: Context) {

    companion object {
        val DARK_MODE = booleanPreferencesKey("dark_mode")
    }

    // Flujo para LEER el valor (asíncrono con Corrutinas)
    val getDarkMode: Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[DARK_MODE] ?: false
        }

    // Función suspendida para GUARDAR (¡Corrutina!)
    suspend fun saveDarkMode(value: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[DARK_MODE] = value
        }
    }
}