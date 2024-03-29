package dev.sunnat629.openai_android.models.chats

import kotlinx.serialization.*

/**
 * Request model for submitting chat completions with image inputs.
 *
 * @param model Specifies the model to use, such as "gpt-4-vision-preview" for image inputs.
 * @param messages A list containing the user's messages, which can include both text and images.
 * @param maxTokens The maximum number of tokens to generate in the chat completion.
 */
@Serializable
data class ChatImageInputRequest(
    @SerialName("model") val model: String,
    @SerialName("messages") val messages: List<ChatMessage>,
    @SerialName("max_tokens") val maxTokens: Int
)

@Serializable
data class ChatMessage(
    @SerialName("role") val role: String,
    @SerialName("content") val content: List<ChatContent>
)

@Serializable
sealed class ChatContent {
    @Serializable
    @SerialName("type")
    data class Text(
        @SerialName("text") val text: String
    ) : ChatContent()

    @Serializable
    @SerialName("type")
    data class ImageUrl(
        @SerialName("image_url") val imageUrl: ImageUrlContent
    ) : ChatContent()

    @Serializable
    data class ImageUrlContent(
        @SerialName("url") val url: String
    )
}

/**
 * Response model for chat completions with image inputs.
 *
 * @param id The unique identifier of the chat completion.
 * @param objectContent Indicates the type of object, typically "chat.completion" for completion responses.
 * @param created Unix timestamp indicating when the completion was created.
 * @param model Specifies the model used for generating the completion.
 * @param choices Contains the list of completion choices, usually including the assistant's responses.
 * @param usage Provides token usage statistics for the completion.
 */
@Serializable
data class ChatImageInputResponse(
    @SerialName("id") val id: String,
    @SerialName("object") val objectContent: String,
    @SerialName("created") val created: Long,
    @SerialName("model") val model: String,
    @SerialName("choices") val choices: List<ChatChoice>,
    @SerialName("usage") val usage: ChatUsage
)

@Serializable
data class ChatMessageResponse(
    @SerialName("role") val role: String,
    @SerialName("content") val content: String,
    @SerialName("tool_calls") val toolCalls: List<ToolCall>? = null
)

@Serializable
data class ToolCall(
    @SerialName("id") val id: String,
    @SerialName("type") val type: String,
    @SerialName("function") val function: FunctionCall
)

@Serializable
data class FunctionCall(
    @SerialName("name") val name: String,
    @SerialName("arguments") val arguments: String
)

@Serializable
data class ChatUsage(
    @SerialName("prompt_tokens") val promptTokens: Int,
    @SerialName("completion_tokens") val completionTokens: Int,
    @SerialName("total_tokens") val totalTokens: Int
)