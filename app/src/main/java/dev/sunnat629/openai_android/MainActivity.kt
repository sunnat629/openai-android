package dev.sunnat629.openai_android

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import dev.sunnat629.openai_android.models.chats.ChatContent
import dev.sunnat629.openai_android.models.chats.ChatRequest
import dev.sunnat629.openai_android.models.chats.ChatContent
import dev.sunnat629.openai_android.models.chats.ChatRequest
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
                val model = remember { mutableStateOf("") }
                val chat = remember { mutableStateOf("") }
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    LaunchedEffect(Unit) {
                        lifecycleScope.launch {
                            openAI.chat
                                .model("gpt-3.5-turbo")
                                .role("user")
                                .text("What's the name of the capital of Bangladesh?")
                                .create()
                                .onSuccess {
                                    model.value = it.model
                                    chat.value =
                                        "${it.choices.first().message.role}: ${it.choices.first().message.content}"
                                    Log.d("ASDF", it.toString())
                                }
                                .onFailure {
                                    chat.value = "Failure: ${it.message}"
                                    Log.e("ASDF", it.toString())
                                }
                        }
                    }

                    Column {
                        Text(
                            text = model.value,
                        )

                        Text(
                            text = chat.value,
                        )
                    }
                            openAI.chat
                                .model("gpt-3.5-turbo")
                                .role("user")
                                .text("What's the name of the capital of Bangladesh?")
                                .create()
                                .onSuccess {
                                    model.value = it.model
                                    chat.value =
                                        "${it.choices.first().message.role}: ${it.choices.first().message.content}"
                                    Log.d("ASDF", it.toString())
                                }
                                .onFailure {
                                    chat.value = "Failure: ${it.message}"
                                    Log.e("ASDF", it.toString())
                                }
                        }
                    }

                    Column {
                        Text(
                            text = model.value,
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = "user: What's the name of the capital of Bangladesh?",
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = chat.value,
                        )
                    }
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