# Wiki: Interacting with OpenAI Audio in Android

This wiki provides detailed guidance on integrating and using OpenAI's audio functionalities within an Android application. It focuses on utilizing OpenAI's APIs for audio tasks such as text-to-speech (TTS), transcription, and translation, demonstrating how to effectively manage these tasks using Kotlin coroutines and Jetpack Compose's `LaunchedEffect`.

## Text-to-Speech (TTS) Example

### Overview

Text-to-Speech technology converts written text into spoken words, providing a user-friendly way to interact with app content. This is particularly useful for accessibility features, audio content creation, and interactive applications.

### Code Example

```kotlin
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
            }
    }
}
```

### Description

- **Model Selection**: Specifies the TTS model.
- **Input Configuration**: Sets the text to be converted into speech.
- **Voice Selection**: Chooses the voice for speech output.
- **Output Handling**: Manages file creation and URI handling for the generated speech.
- **Lifecycle Management**: Ensures the operation adapts to the lifecycle events of the host UI component.

## Transcription Example

Transcription converts spoken language into written text, enabling content indexing, searchable media, and accessibility features.

### Code Example

```kotlin
LaunchedEffect(audioUrl.value) {
    lifecycleScope.launch {
        if (audioUrl.value == null) return@launch
        openAI.audio
            .model("whisper-1")
            .file(context, audioUrl.value!!)
            .transcription()
            // Handlers (onStart, catch, collect) as above
    }
}
```

### Description

- **Trigger Condition**: Executes when `audioUrl` changes.
- **Model Application**: Utilizes the transcription model.
- **File Handling**: Processes audio files from URIs.
- **Operational Flow**: Manages transcription from audio input to text output.

## Translation Example

Translation extends the functionality of transcription by converting spoken words from one language to another, enhancing accessibility and understanding across language barriers.

### Code Example

```kotlin
LaunchedEffect(audioFileUri) {
    lifecycleScope.launch {
        if (audioFileUri.value == null) return@launch
        openAI.audio
            .model("translation-1")
            .file(context, audioFileUri.value!!)
            .prompt("Translate this text")
            .temperature(0.5) // Adjusts response creativity.
            .translation()
            // Handlers (onStart, catch, collect) as above
    }
}
```

### Description

- **Trigger Mechanism**: Activated when `audioFileUri` is updated.
- **Model Configuration**: Sets the model for translation.
- **Prompt Setting**: Provides context for the translation.
- **Temperature Control**: Influences the variability in the translation output.

## Conclusion

Integrating OpenAI's audio capabilities into Android applications allows developers to leverage advanced AI technologies for creating interactive and accessible content. By following this guide, developers can implement sophisticated audio processing workflows, enhancing user engagement and expanding the app's functionality.
