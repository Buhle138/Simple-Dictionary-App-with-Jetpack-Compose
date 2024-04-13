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
                response.body()?.first()?.let {
                    state.value = state.value.copy(
                        definition = it.meanings.get(0).definitions.get(0).definition
                    )
                }
            }catch (e: Exception){

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