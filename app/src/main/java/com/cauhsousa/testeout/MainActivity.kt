package com.cauhsousa.testeout

import android.graphics.drawable.Icon
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cauhsousa.testeout.ui.theme.TesteOutTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TesteOutTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    var text by rememberSaveable { mutableStateOf("") }
    Column {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
        out(text, {text = it})
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun out(value: String, aoMudar: (String) -> Unit) {
//    var text by rememberSaveable { mutableStateOf("") }
    var isError by rememberSaveable { mutableStateOf(false) }

    fun validate(text: String) {
        isError = text.length > 10 || text.isEmpty()
    }

    Column {
        TextField(
            value = value,
            onValueChange = {
                aoMudar(it)
                isError = false
            },
            trailingIcon = {
                if (isError)
                    Icon(Icons.Filled.Info, "Error", tint = MaterialTheme.colorScheme.error)
            },
            singleLine = true,
            isError = isError,
            keyboardActions = KeyboardActions { validate(value) },
        )
        if (isError) {
            Text(
                text = "Error message",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
    }
}