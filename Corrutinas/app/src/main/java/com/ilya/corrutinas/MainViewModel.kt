package com.ilya.corrutinas

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel: ViewModel() {
    var resultState by mutableStateOf("")
        private set

    var isLoading by mutableStateOf(false)
        private set


    fun fetchData(){
        //Nos permite crear una corrutina directamente de lo que es un view model
        viewModelScope.launch {
            try{
                isLoading = true
                llamarApi()
            }catch (e:Exception){
                println("Error ${e.message}")
            } finally {
                isLoading = false

            }


            //llamarApi()
           /* val result = withContext(Dispatchers.IO){
                delay(5000)
                "Respuesta de la API"
            }
            resultState = result*/
        }
    }

    //Solo funcionan dentro de una corrutina u otra función suspend
   private suspend fun llamarApi(){
        val result = withContext(Dispatchers.IO){
            delay(5000)
            "Respuesta de la API"
        }
        resultState = result
    }


    fun bloqueoApp(){
        //Espera 5 segundos
        Thread.sleep(5000)
        //Devuelve el mensaje después de los 5 segundos
        resultState = "Respuesta de la API"
    }
}