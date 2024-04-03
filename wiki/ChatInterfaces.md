# OpenAI Chat API Client Interfaces

This document outlines the interfaces designed for an Android client API to interact with the OpenAI Chat API, facilitating a structured approach to building chat functionality within Android applications.

## BaseUseCases Interface

The `BaseUseCases<T>` interface provides a generic blueprint for configuring use cases, allowing for flexible and extensible setup tailored to specific needs.

### Interface Definition

```kotlin
interface BaseUseCases<T> {
    fun model(model: String): T
    fun role(role: String): T
    fun text(text: String): T
    fun imageUrl(imageUrl: String): T
}
```

### Key Methods

- `model(model: String)`: Configures the model to be used, e.g., "gpt-3.5-turbo".
- `role(role: String)`: Specifies the role within the chat, such as "user" or "assistant".
- `text(text: String)`: Sets the initial text for the chat or other operations.
- `imageUrl(imageUrl: String)`: Provides an image URL for use cases involving image processing.

## Chat Interface

Extends `BaseUseCases<Chat>` to add chat-specific configurations, enhancing the control over chat session behavior.

### Interface Definition

```kotlin
interface Chat : BaseUseCases<Chat> {
    suspend fun stream(enable: Boolean, delay: Long = 200L): Chat
    suspend fun logprobs(enable: Boolean, topLogprobs: Int = 2): Chat
    suspend fun maxTokens(maxTokens: Int): Chat
    suspend fun toolChoice(toolChoice: String): Chat
    suspend fun tools(tools: List<FunctionTool>): Chat

    fun create(): Flow<ChatResponse>
}
```

### Key Methods

- `stream(enable: Boolean, delay: Long)`: Enables/disables response streaming with an optional delay.
- `logprobs(enable: Boolean, topLogprobs: Int)`: Activates log probabilities, specifying the top count to return.
- `maxTokens(maxTokens: Int)`: Limits the number of tokens in the chat response.
- `toolChoice(toolChoice: String)`: Selects a specific tool or function for the chat session.
- `tools(tools: List<FunctionTool>)`: Sets a list of tools or functions to be utilized.
- `create()`: Initiates the chat session with the configured parameters, returning a flow of responses.

These interfaces provide a comprehensive framework for integrating and utilizing the OpenAI Chat API in Android applications, offering clear contracts for implementation and ensuring flexibility and extensibility in chat functionalities.