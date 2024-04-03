package dev.sunnat629.openai_client

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import dev.sunnat629.openai_client.networks.onFailure
import dev.sunnat629.openai_client.networks.onSuccess
import dev.sunnat629.openai_client.ui.theme.OpenAiAndroidTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
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
                val modelMain = remember { mutableStateOf("") }
                val chat = remember { mutableStateOf("") }
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    LaunchedEffect(Unit) {
//                        lifecycleScope.launch {
//                            openAI.models
//                                .model("gpt-3.5-turbo")
//                                .role("user")
//                                .text("What's the name of the capital of Bangladesh?")
//                                .getModels()
//                                .onSuccess {
//                                    modelMain.value = it.data.first().id
//                                    Log.d("ASDF", it.toString())
//                                }
//                                .onFailure {
//                                    modelMain.value = "Failure: ${it.message}"
//                                    Log.e("ASDF", it.toString())
//                                }
//                        }
                    }
                }


                LaunchedEffect(Unit) {
                    lifecycleScope.launch {
                        openAI.chat
                            .model("gpt-3.5-turbo")
                            .role("user")
                            .text("What's the name of the capital of Bangladesh?")
                            .stream(true)
                            .chatSteam()
                            .collect {
                                it.onSuccess { chatResponse ->
                                    model.value = chatResponse.model.toString()
                                    lifecycleScope.launch {
                                        chatResponse.choices?.firstOrNull()?.delta?.content?.takeIf { true }
                                            ?.let { content ->
                                                delay(1000)
                                                chat.value += content
                                                Log.e("ASDF", content) }



                                    }
                                }
                                    .onFailure { error ->
                                        chat.value = "Failure: ${error.message}"
                                        Log.e("ASDF", error.toString())
                                    }
                            }
                    }
                }

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                        .padding(10.dp)
                ) {
                    Text(
                        text = "modelMain: ${modelMain.value}",
                    )

                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "model: ${model.value}",
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "user: What's the name of the capital of Bangladesh?",
                    )

                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = chat.value,
                    )
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