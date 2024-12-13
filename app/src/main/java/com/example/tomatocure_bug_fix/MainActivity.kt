package com.example.tomatocure_bug_fix

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tomatocure_bug_fix.ui.theme.Tomatocure_bug_fixTheme

class MainActivity : ComponentActivity() {

    private lateinit var galleryResultLauncher: ActivityResultLauncher<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize the ActivityResultLauncher
        galleryResultLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let {
                println("Picked image URI: $it")
            } ?: run {
                println("No image selected")
            }
        }

        setContent {
            Tomatocure_bug_fixTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }

    @Composable
    fun MainScreen() {
        val selectedImageUri = remember { mutableStateOf<Uri?>(null) }

        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            Greeting(name = "Tomato Cure")

            Button(onClick = { galleryResultLauncher.launch("image/*") }) {
                Text(text = "Pick an Image")
            }

            selectedImageUri.value?.let { uri ->
                Text(text = "Selected Image: $uri")
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Tomatocure_bug_fixTheme {
        Greeting("Android")
    }
}