package dev.sunnat629.openai_client

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import dev.sunnat629.openai_client.ui.theme.OpenAiAndroidTheme
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val openAI = OpenAIBuilder(BuildConfig.OPEN_AI_TOKEN).build()

        setContent {
            OpenAiAndroidTheme {
                // A surface container using the 'background' color from the theme
                val model = remember { mutableStateOf("") }
                val modelMain = remember { mutableStateOf("") }
                val chat = remember { mutableStateOf("") }
                val loading = remember { mutableStateOf(true) }

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    LaunchedEffect(Unit) {
                        lifecycleScope.launch {
                            openAI.audio
                                .input(listOf(
                                    "I want to be a footballer.",
                                    "I want to kill you."
                                ))
                                .moderate()
                                .onStart {
                                    loading.value = true
                                }
                                .catch { exception ->
                                    loading.value = false
                                    chat.value = "Failure: ${exception.message}"
                                }
                                .collect { response ->
                                    loading.value = false
                                    chat.value = response.toString()
                                    model.value = "flagged: ${response.results?.map { it?.flagged }}"
                                }
                        }
                    }
//                    ApiSamples(openAI, loading, modelMain, chat, model)

                    if (loading.value) CircularProgressIndicator()
                    else {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(10.dp)
                        ) {
                            item {
                                Text(
                                    text = "modelMain: ${modelMain.value}",
                                )

                                Spacer(modifier = Modifier.height(10.dp))
                            }
                            item {

                                Text(
                                    text = "model: ${model.value}",
                                )

                                Spacer(modifier = Modifier.height(10.dp))

                            }
                            item {
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
        }
    }

    @Composable
    private fun ApiSamples(
        openAI: OpenAI,
        loading: MutableState<Boolean>,
        modelMain: MutableState<String>,
        chat: MutableState<String>,
        model: MutableState<String>
    ) {
        LaunchedEffect(Unit) {
            lifecycleScope.launch {
                openAI.models
                    .getModels()
                    .onStart {
                        Log.w("ASDF", "onStart")
                        loading.value = true
                    }
                    .catch {
                        loading.value = false
                        modelMain.value = "Failure: ${it.message}"
                        Log.e("ASDF", it.toString())
                    }
                    .collect {
                        loading.value = false
                        modelMain.value = it.data.first().id
                        Log.d("ASDF", it.toString())
                    }
            }
        }

        LaunchedEffect(Unit) {
            lifecycleScope.launch {
                openAI.models
                    .model("gpt-3.5-turbo")
                    .retrieveModel()
                    .onStart {
                        loading.value = true
                    }
                    .catch { exception ->
                        loading.value = false
                        chat.value = "Failure: ${exception.message}"
                    }
                    .collect { response ->
                        loading.value = false
                        model.value = response.toString()
                        chat.value = response.objectContent.plus(": ${response.id} || ownedBy: ${response.ownedBy}")
                    }
            }
        }

        LaunchedEffect(Unit) {
            lifecycleScope.launch {
                openAI.chat
                    .model("gpt-3.5-turbo")
                    .role("user")
                    .text("What's the name of the capital of Bangladesh?")
                    .stream(true)
                    .create()
                    .onStart {
                        Log.w("ASDF", "onStart")
                        loading.value = true
                    }
                    .catch { exception ->
                        Log.w("ASDF", "catch")
                        loading.value = false
                        chat.value = "Failure: ${exception.message}"
                    }
                    .collect { chatResponse ->
                        Log.w("ASDF", "collect")
                        loading.value = false
                        model.value = chatResponse.model.toString()
                        chatResponse.choices?.firstOrNull()?.delta?.content?.takeIf { true }
                            ?.let { content ->
                                chat.value += content
                            }
                    }
            }
        }

        LaunchedEffect(Unit) {
            lifecycleScope.launch {
                openAI.chat
                    .model("gpt-3.5-turbo")
                    .role("user")
                    .text("What's the name of the capital of Bangladesh?")
                    .create()
                    .onStart {
                        loading.value = true
                    }
                    .catch { exception ->
                        loading.value = false
                        chat.value = "Failure: ${exception.message}"
                    }
                    .collect { chatResponse ->
                        loading.value = false
                        model.value = chatResponse.model.toString()
                        chat.value =
                            "${chatResponse.choices?.firstOrNull()?.message?.role}: ${chatResponse.choices?.firstOrNull()?.message?.content}"
                    }
            }
        }

        LaunchedEffect(Unit) {
            lifecycleScope.launch {
                openAI.chat
                    .model("gpt-3.5-turbo")
                    .role("user")
                    .text("What's the name of the capital of Bangladesh?")
                    .logprobs(true, 2)
                    .create()
                    .onStart {
                        loading.value = true
                    }
                    .catch { exception ->
                        loading.value = false
                        chat.value = "Failure: ${exception.message}"
                    }
                    .collect { chatResponse ->
                        loading.value = false
                        model.value = chatResponse.model.toString()
                        chat.value =
                            "${chatResponse.choices?.firstOrNull()?.message?.role}: ${chatResponse.choices?.firstOrNull()?.message?.content}"
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