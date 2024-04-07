package dev.sunnat629.openai_client

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.FrameLayout
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import dev.sunnat629.openai_client.models.audio.TTSModel
import dev.sunnat629.openai_client.models.audio.Voice
import dev.sunnat629.openai_client.ui.theme.OpenAiAndroidTheme
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import java.io.File
import androidx.activity.result.PickVisualMediaRequest
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import dev.sunnat629.openai_client.models.audio.ResponseFormatString
import dev.sunnat629.openai_client.models.audio.TimestampGranularity

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val openAI = OpenAIBuilder(BuildConfig.OPEN_AI_TOKEN).build()

        setContent {
            OpenAiAndroidTheme {

                val context = LocalContext.current
                // A surface container using the 'background' color from the theme
                val model = remember { mutableStateOf("") }
                val modelMain = remember { mutableStateOf("") }
                val chat = remember { mutableStateOf("") }
                val loading = remember { mutableStateOf(false) }
                val audioUrl = remember { mutableStateOf<Uri?>(null) }

                val exoPlayer = remember { ExoPlayer.Builder(context).build() }


                val isStoragePermissionGranted = remember { mutableStateOf(hasStoragePermission(context)) }

                val storagePermissionState = remember {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        arrayOf(Manifest.permission.READ_MEDIA_VIDEO, Manifest.permission.READ_MEDIA_AUDIO)
                    } else arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                }

                val launchPermissionRequest = rememberLauncherForActivityResult(
                    contract = ActivityResultContracts.RequestMultiplePermissions()
                ) { permissionsMap ->
                    // Handle the permissions result
                    val granted = permissionsMap.entries.all { it.value }
                    isStoragePermissionGranted.value = granted
                }

                LaunchedEffect(Unit) {
                    launchPermissionRequest.launch(storagePermissionState)
                }

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    DisposableEffect(exoPlayer) {
                        onDispose {
                            exoPlayer.release() // Don't forget to release the player when it's no longer needed.
                        }
                    }

                    LaunchedEffect(audioUrl.value) {
                        lifecycleScope.launch {
                            if (audioUrl.value == null) return@launch
                            Log.e("ASDF", "*******")
                            Log.e("ASDF", audioUrl.value.toString())
                            openAI.audio
                                .model("whisper-1")
                                .file(context, audioUrl.value!!)
//                                .timestampGranularities(TimestampGranularity.WORD)
                                .responseFormatString(ResponseFormatString.TEXT)
                                .transcription()
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
                                    Log.e("ASDF", response.toString())
                                    model.value = TTSModel.TTS1.value
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
                                MySendMessageUI(audioUrl)
                            }

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
                                if (audioUrl.value != null)  {
                                    AudioPlayerComposable(context, audioUrl.value!!)
                                }
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
    fun AudioPlayerComposable(context: Context, audioFileUri: Uri) {
        // Remember ExoPlayer instance with the context
        val exoPlayer = remember {
            ExoPlayer.Builder(context).build().apply {
                // Prepare the player with the source.
                val mediaItem = MediaItem.fromUri(audioFileUri)
                setMediaItem(mediaItem)
                prepare()
            }
        }

        // AndroidView to display ExoPlayer
        AndroidView(
            modifier = Modifier.fillMaxWidth(),
            factory = { ctx ->
                PlayerView(ctx).apply {
                    layoutParams = FrameLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT)
                    player = exoPlayer
                    useController = true // to display playback controls
                }
            }
        )

        // Dispose ExoPlayer when composable is disposed
        DisposableEffect(exoPlayer) {
            onDispose {
                exoPlayer.release()
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
        val context = LocalContext.current
        val audioUrl = remember { mutableStateOf<Uri?>(null) }

        LaunchedEffect(Unit) {
            lifecycleScope.launch {
                openAI.audio
                    .model(TTSModel.TTS1)
                    .input("I LOVE YOU...")
                    .voice(Voice.ECHO)
                    .speechWithUri(context)
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
                        audioUrl.value = response
                        Log.e("ASDF", response.toString())
                        model.value = TTSModel.TTS1.value
                    }
            }
        }

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

    @Composable
    fun MySendMessageUI(audioUrl: MutableState<Uri?>) {
        var text by remember { mutableStateOf("") }
        var audioUri by remember { mutableStateOf<Uri?>(null) }
        var imageUri by remember { mutableStateOf<Uri?>(null) }

        // Launchers for picking audio and image
        val pickAudioLauncher = rememberLauncherForActivityResult(ActivityResultContracts.OpenDocument()) { uri: Uri? ->
            audioUri = uri
            text = "Audio is attached"
        }

        val pickImageLauncher = rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri: Uri? ->
            imageUri = uri
            text = "Image is attached"
        }

        Column(modifier = Modifier.padding(16.dp)) {
            BasicTextField(
                value = text,
                onValueChange = { text = it },
                modifier = Modifier.padding(bottom = 8.dp),
                decorationBox = { innerTextField ->
                    Row {
                        if (text.isEmpty()) Text("Type a message...", color = Color.Gray)
                        innerTextField()
                    }
                }
            )

            Row {
                Button(onClick = { pickAudioLauncher.launch(arrayOf("audio/*")) }) {
//                Button(onClick = { pickAudioLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.VideoOnly)) }) {
                    Text("Attach Audio")
                }
                Button(onClick = { pickImageLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)) },
                    modifier = Modifier.padding(start = 8.dp)) {
                    Text("Attach Image")
                }
            }

            Button(
                onClick = {
                    // Implement the logic to convert the text, audioUri, and imageUri to byte array and send via API
                    // For demonstration, we're just printing the Uris
                    println("Sending text: $text")
                    audioUri?.let { println("Audio Uri: $it") }
                    imageUri?.let { println("Image Uri: $it") }

                    // Reset after sending
                    text = ""
                    audioUrl.value = audioUri
                    audioUri = null
                    imageUri = null
                },
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Text("Send")
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


fun hasStoragePermission(context: Context): Boolean =
    ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) ==
            PackageManager.PERMISSION_GRANTED
