package com.ilya.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class StoreUserEmail (private val context: Context){
    //Caracteristica de Kotlin para definir miembros de una clase sin la necesidad de intanciar la clase
    companion object {
        private val Context.dataStore : DataStore<Preferences> by preferencesDataStore("UserEmail")
        val USER_EMAIL = stringPreferencesKey("user_email")
    }

    val getEmail: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[USER_EMAIL]
        }

    suspend fun saveEmail(email : String){
        context.dataStore.edit { preferences ->
           preferences[USER_EMAIL] = email
        }
    }

}