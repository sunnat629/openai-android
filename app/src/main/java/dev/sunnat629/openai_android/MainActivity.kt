package dev.sunnat629.openai_android

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import dev.sunnat629.openai_android.networks.onFailure
import dev.sunnat629.openai_android.networks.onSuccess
import dev.sunnat629.openai_android.ui.theme.OpenAiAndroidTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val openAI = OpenAIBuilder(BuildConfig.OPEN_AI_TOKEN)
            .build()

        setContent {
            OpenAiAndroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LaunchedEffect(Unit) {
                        lifecycleScope.launch {
                            openAI.retrieveModel("dall-e-2")
                                .onSuccess { Log.d("ASDF", it.toString()) }
                                .onFailure { Log.e("ASDF", it.toString()) }
                        }
                    }
                    Greeting("Android")
                }
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
    OpenAiAndroidTheme {
        Greeting("Android")
    }
}