package com.example.simpledictionarywithjetpackcompose

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.lang.Exception

class DictionaryViewModel : ViewModel() {

    val state = mutableStateOf(MyScreenState())

    fun updateText(newText: String){
        state.value = state.value.copy(textState = newText)
    }


    public  fun fetchDefinitions(wordSearch: String){

        viewModelScope.launch {
            try{
                val response = dictionaryResponse.getDefinition(wordSearch)
                state.value = state.value.copy(
                    definition = response.body().toString()
                )
               Log.i("Success Response from API", response.body().toString())
            }catch (e: Exception){
                Log.i("Error response from API", wordSearch)
                state.value = state.value.copy(
                    error = "Error fetching definitions ${e.message}"
                )
            }
        }
    }

}
data class MyScreenState(
    var textState: String = "",
    var definition: String = "",
    val error: String? = null
)