# Audio Interface Wiki

## Overview

The `Audio` interface in an Android environment provides a comprehensive API to interact with OpenAI's audio capabilities. This interface facilitates various audio operations such as text-to-speech generation, audio transcription, and translation, enabling developers to integrate sophisticated audio features into their applications.

## Functionalities of the Audio Interface

### Model Selection

```kotlin
fun model(model: TTSModel): Audio
fun model(model: String): Audio
```

- **Purpose**: Selects the model to be used for the audio operation, accommodating both predefined and custom model types.
- **Parameters**:
  - `model`: Accepts a model from predefined `TTSModel` or a `String` representing the model identifier.
- **Return**: Returns an instance of `Audio` for method chaining.

### Input Text

```kotlin
fun input(input: String): Audio
```

- **Purpose**: Sets the text to be converted into speech or used as input for other audio operations.
- **Parameters**:
  - `input`: The text input for generating audio, limited to a maximum of 4096 characters.
- **Return**: Returns an instance of `Audio` for method chaining.

### Voice Selection

```kotlin
fun voice(voice: Voice): Audio
```

- **Purpose**: Specifies the voice to be used in audio generation, supporting diverse options like alloy, echo, etc.
- **Parameters**:
  - `voice`: Enum or identifier for the desired voice type.
- **Return**: Returns an instance of `Audio` for method chaining.

### File Handling

```kotlin
fun file(context: Context, file: Uri): Audio
```

- **Purpose**: Sets the audio file for operations like transcription or translation.
- **Parameters**:
  - `context`: The Android context.
  - `file`: URI pointing to the audio file.
- **Return**: Returns an instance of `Audio` for method chaining.

### Prompt Setting

```kotlin
fun prompt(prompt: String): Audio
```

- **Purpose**: Guides the model’s response style or continues from a previous segment.
- **Parameters**:
  - `prompt`: Text to guide or influence the model's output.
- **Return**: Returns an instance of `Audio` for method chaining.

### Response Format

```kotlin
fun responseFormat(format: ResponseFormat): Audio
fun responseFormatString(format: ResponseFormatString): Audio
```

- **Purpose**: Defines the output format of the audio or transcript.
- **Parameters**:
  - `format`: The desired format for the response, such as MP3 (audio) or JSON (transcript).
- **Return**: Returns an instance of `Audio` for method chaining.

### Speed Adjustment

```kotlin
fun speed(speed: Double): Audio
```

- **Purpose**: Modifies the playback speed of the generated audio.
- **Parameters**:
  - `speed`: Speed factor ranging from 0.25 to 4.0.
- **Return**: Returns an instance of `Audio` for method chaining.

### Timestamp Granularities

```kotlin
fun timestampGranularities(timestampGranularity: TimestampGranularity): Audio
fun timestampGranularities(list: List<TimestampGranularity>): Audio
```

- **Purpose**: Specifies how timestamps are included in the transcription response.
- **Parameters**:
  - `list`: A list specifying granularities such as word-level or segment-level timestamps.
- **Return**: Returns an instance of `Audio` for method chaining.

### Temperature Setting

```kotlin
fun temperature(temperature: Double): Audio
```

- **Purpose**: Influences the creativity and variability in the generated audio responses.
- **Parameters**:
  - `temperature`: Creativity level, typically ranging from 0 to 1.
- **Return**: Returns an instance of `Audio` for method chaining.

### Asynchronous Operations

```kotlin
suspend fun speech(): Flow<ByteArray>
suspend fun speechWithUri(context: Context): Flow<Uri>
suspend fun transcription(): Flow<TranscriptionResponse>
suspend fun translation(): Flow<TranslationResponse>
```

- **Purpose**: These methods provide asynchronous handling of audio generation, speech file retrieval, transcription, and translation.
- **Usage**: Use these functions to execute audio tasks asynchronously, supporting reactive data handling within the application.

## Conclusion

The `Audio` interface offers a robust framework for managing various audio-related tasks in Android applications, leveraging OpenAI's advanced audio processing capabilities. This interface ensures that developers can implement detailed and flexible audio functionalities efficiently, enhancing the multimedia capabilities of their applications and providing users with rich interactive experiences.
