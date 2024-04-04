## Wiki: Interacting with OpenAI Chat in Android

Integrating OpenAI's Chat API into your Android application using Kotlin allows you to leverage the power of GPT-3.5 (or the latest models) for generating human-like text based on the input provided. This wiki covers how to set up and use the Chat API to interact with the OpenAI service in a Kotlin-based Android project.

### Implementing the Chat Functionality

The core of the integration involves creating an instance of a chat configuration object, setting various parameters, and then initiating the chat.

#### Base Setup

```kotlin
val openAI = OpenAI() // Your OpenAI service wrapper instance
```

#### 1. Simple Text Queries

To send a simple text query and receive a response:

```kotlin
LaunchedEffect(Unit) {
    lifecycleScope.launch {
        openAI.chat
            .model("gpt-3.5-turbo")
            .role("user")
            .text("What's the name of the capital of Bangladesh?")
            .create()
            .onStart { loading.value = true }
            .catch { exception ->
                loading.value = false
                chat.value = "Failure: ${exception.message}"
            }
            .collect { chatResponse ->
                loading.value = false
                chat.value = chatResponse.choices?.firstOrNull()?.message?.content
            }
    }
}
```

#### 2. Streaming Responses

For handling streaming responses where the conversation continues over time:

```kotlin
LaunchedEffect(Unit) {
    lifecycleScope.launch {
        openAI.chat
            .model("gpt-3.5-turbo")
            .role("user")
            .text("What's the name of the capital of Bangladesh?")
            .stream(true, 200) // Enable streaming, 200ms delay
            .create()
            // Handlers (onStart, catch, collect) as above
    }
}
```

#### 3. Advanced Features: Log Probabilities

To use advanced features such as log probabilities:

```kotlin
LaunchedEffect(Unit) {
    lifecycleScope.launch {
        openAI.chat
            .model("gpt-3.5-turbo")
            .role("user")
            .text("What's the name of the capital of Bangladesh?")
            .logprobs(true, 2) // Enable log probabilities
            .create()
            // Handlers (onStart, catch, collect) as above
    }
}
```

### Handling Responses

The response from the OpenAI service is captured in the `collect` block, where you can process and display it as needed in your UI. Ensure proper handling of loading states and errors to enhance user experience.

### Note

- `loading` and `chat` are state holders you should define based on your UI architecture (e.g., using `MutableState` in Jetpack Compose).
- This example uses Kotlin Coroutines for asynchronous operations. Ensure you're familiar with coroutine basics and lifecycle-aware components in Android to prevent memory leaks.

### [Official OpenAi API Reference for Chat](https://platform.openai.com/docs/api-reference/chat)
