package dev.sunnat629.openai_android.models.chats

import dev.sunnat629.openai_android.apis.chats.ChatMessageDelta
import dev.sunnat629.openai_android.apis.chats.ChatStreamingChoice
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Request model for submitting a chat completion with streaming enabled.
 *
 * @param model Specifies the model to use, such as "gpt-3.5-turbo" for streaming.
 * @param messages A list containing the system and user messages for initiating the chat.
 * @param stream Indicates whether the response should be streamed.
 */
@Serializable
data class ChatImageStreamingRequest(
    @SerialName("model") val model: String,
    @SerialName("messages") val messages: List<ChatMessage>,
    @SerialName("stream") val stream: Boolean
)

/**
 * Response model for chat completions when streaming is enabled.
 * The response consists of multiple chunks, each representing a part of the completion.
 *
 * @param id The unique identifier of the chat completion.
 * @param objectContent Indicates the type of object, "chat.completion.chunk" for streamed responses.
 * @param created Unix timestamp indicating when the chunk was created.
 * @param model Specifies the model used for generating the completion chunk.
 * @param choices Contains the list of completion choices with deltas for the streamed response.
 */
@Serializable
data class ChatImageStreamingResponse(
    @SerialName("id") val id: String,
    @SerialName("object") val objectContent: String,
    @SerialName("created") val created: Long,
    @SerialName("model") val model: String,
    @SerialName("choices") val choices: List<ChatStreamingChoice>
)

@Serializable
data class ChatStreamingChoice(
    @SerialName("index") val index: Int,
    @SerialName("delta") val delta: ChatMessageDelta,
    @SerialName("finish_reason") val finishReason: String? = null
)

@Serializable
data class ChatMessageDelta(
    @SerialName("role") val role: String? = null,
    @SerialName("content") val content: String? = null
)
