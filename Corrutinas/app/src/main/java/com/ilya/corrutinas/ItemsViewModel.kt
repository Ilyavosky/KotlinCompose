package com.ilya.corrutinas

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ItemsViewModel: ViewModel() {
    //Otra forma de declarar variables con Flow en tiempo real

    private val _lista: MutableStateFlow<List<ItemsModel>> = MutableStateFlow(emptyList())
    val lista = _lista


    var itemList = mutableStateListOf(ItemsModel())
        private set

    var isLoading by mutableStateOf(false)
        private set


    /*init {
        fetchData()
    }*/

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
        }
    }

    //Solo funcionan dentro de una corrutina u otra función suspend
    private suspend fun llamarApi(){
        val result = withContext(Dispatchers.IO){
            delay(5000)
            listOf(
                ItemsModel(1, "Elemenot 1"), ItemsModel(2, "Elemenot 2"), ItemsModel(3, "Elemenot 3"), ItemsModel(4, "Elemenot 4"), ItemsModel(5, "Elemenot 5"),
                ItemsModel(6, "Elemenot 6"), ItemsModel(7, "Elemenot 7"), ItemsModel(8, "Elemenot 8"), ItemsModel(9, "Elemenot 9"), ItemsModel(10, "Elemenot 10"),
                ItemsModel(11, "Elemenot 11"), ItemsModel(12, "Elemenot 12"), ItemsModel(13, "Elemenot 13"), ItemsModel(14, "Elemenot 14"), ItemsModel(15, "Elemenot 15"),
                ItemsModel(16, "Elemenot 16"), ItemsModel(17, "Elemenot 17"), ItemsModel(18, "Elemenot 18"), ItemsModel(19, "Elemenot 19"), ItemsModel(20, "Elemenot 20"),
                ItemsModel(21, "Elemenot 21"), ItemsModel(22, "Elemenot 22"), ItemsModel(23, "Elemenot 23"), ItemsModel(24, "Elemenot 24"), ItemsModel(25, "Elemenot 25"),
                ItemsModel(26, "Elemenot 26"), ItemsModel(27, "Elemenot 27"), ItemsModel(28, "Elemenot 28"), ItemsModel(29, "Elemenot 29"), ItemsModel(30, "Elemenot 30"), ItemsModel(31, "Elemenot 31"), ItemsModel(32, "Elemenot 32"), ItemsModel(33, "Elemenot 33"), ItemsModel(34, "Elemenot 34"),
                ItemsModel(35, "Elemenot 35"), ItemsModel(36, "Elemenot 36"), ItemsModel(37, "Elemenot 37"), ItemsModel(38, "Elemenot 38"), ItemsModel(39, "Elemenot 39"), ItemsModel(40, "Elemenot 40"), ItemsModel(41, "Elemenot 41"), ItemsModel(42, "Elemenot 42"), ItemsModel(43, "Elemenot 43"), ItemsModel(44, "Elemenot 44"),
                ItemsModel(45, "Elemenot 45"), ItemsModel(46, "Elemenot 46"), ItemsModel(47, "Elemenot 47"), ItemsModel(48, "Elemenot 48"), ItemsModel(49, "Elemenot 49"), ItemsModel(50, "Elemenot 50")
            )
        }
        itemList.addAll(result)
        _lista.value = result
    }
}