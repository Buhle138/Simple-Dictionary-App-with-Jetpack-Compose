package com.example.simpledictionarywithjetpackcompose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.example.simpledictionarywithjetpackcompose.ui.theme.SimpleDictionaryWithJetpackComposeTheme

class MainActivity : ComponentActivity() {

    val myViewModel by lazy{
        ViewModelProvider(this).get(DictionaryViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimpleDictionaryWithJetpackComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                UserInterface(myViewModel)
                }
            }
        }
    }
}

@Composable
fun UserInterface(myViewModel : DictionaryViewModel){

        Column {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(2.dp),
                value = myViewModel.state.value.textState,
                onValueChange = {myViewModel.updateText(it)})

            Button(onClick = {
               myViewModel.fetchDefinitions(myViewModel.state.value.textState)
            }) {
                Text("Search")
            }

                Text(text = myViewModel.state.value.definition)
            Text(text = myViewModel.state.value.error.toString())

        }

}