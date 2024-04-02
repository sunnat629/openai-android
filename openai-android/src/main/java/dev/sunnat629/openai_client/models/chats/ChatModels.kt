/**
 * @author  Mohi Us Sunnat
 * @date    01.04.24
 * Copyright ©2024 Sunnat629.dev. All rights reserved.
 */

package dev.sunnat629.openai_client.models.chats

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

/**
 * Request model for creating a chat completion that may incorporate various features such as streaming,
 * image inputs, log probabilities, and specified function invocations.
 *
 * @property model Specifies the model to use, such as "gpt-3.5-turbo" for diverse requests including streaming and function invocations.
 * @property messages Contains the system and user messages initiating the chat, which can include text and images.
 * @property stream Indicates if responses should be streamed. Nullable for non-streaming requests.
 * @property maxTokens Maximum tokens to generate in the chat completion. Nullable when not specified.
 * @property logprobs Includes log probabilities in the response. Nullable for requests without this requirement.
 * @property topLogprobs Number of likely tokens to return at each position. Nullable if logprobs is not applied.
 * @property tools List of functions model may call in response. Nullable for requests without function calls.
 * @property toolChoice Method for choosing between message generation or function call. Nullable if tools are not used.
 */
@Serializable
data class ChatRequest(
    @SerialName("model") val model: String,
    @SerialName("messages") val messages: List<ChatMessage>,
    @SerialName("stream") val stream: Boolean? = null,
    @SerialName("max_tokens") val maxTokens: Int? = null,
    @SerialName("logprobs") val logprobs: Boolean? = null,
    @SerialName("top_logprobs") val topLogprobs: Int? = null,
    @SerialName("tools") val tools: List<FunctionTool>? = null,
    @SerialName("tool_choice") val toolChoice: String? = null
)

/**
 * Unified response model for various types of chat completions, including standard completions,
 * completions with streaming, and completions involving image inputs. It captures common fields
 * across different response types and includes optional fields for specific scenarios.
 *
 * @property id Unique identifier for the chat completion.
 * @property objectContent Indicates the type of object, such as "chat.completion" for standard completions,
 * "chat.completion.chunk" for streamed responses, and tailored types for other specific responses.
 * @property created Unix timestamp of when the completion was created.
 * @property model Model used for generating the completion.
 * @property choices List of completion choices, varying in details (e.g., message content, streaming deltas).
 * @property usage Token usage statistics for the completion, applicable in scenarios like image inputs.
 * @property toolCalls Optional list of tool calls made during the chat, applicable for certain response types.
 */
@Serializable
data class ChatResponse(
    @SerialName("id") val id: String,
    @SerialName("object") val objectContent: String,
    @SerialName("created") val created: Long,
    @SerialName("model") val model: String,
    @SerialName("choices") val choices: List<ChatChoice>,
    @SerialName("usage") val usage: ChatUsage? = null,
    @SerialName("tool_calls") val toolCalls: List<ToolCall>? = null,
    @SerialName("system_fingerprint") val systemFingerprint: String? = null,
)

@Serializable
data class ChatChoice(
    @SerialName("message") val message: ChatMessageResponse,
    @SerialName("logprobs") val logprobs: JsonElement? = null, // Could be null or contain log probability information
    @SerialName("finish_reason") val finishReason: String,
    @SerialName("index") val index: Int
)

@Serializable
data class FunctionTool(
    @SerialName("type") val type: String,
    @SerialName("function") val function: ToolFunction
)

@Serializable
data class ToolFunction(
    @SerialName("name") val name: String,
    @SerialName("description") val description: String,
    @SerialName("parameters") val parameters: FunctionParameters
)

@Serializable
data class FunctionParameters(
    @SerialName("type") val type: String,
    @SerialName("properties") val properties: Map<String, FunctionParameterProperty>,
    @SerialName("required") val required: List<String>
)

@Serializable
data class FunctionParameterProperty(
    @SerialName("type") val type: String,
    @SerialName("description") val description: String,
    @SerialName("enum") val enum: List<String>? = null
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

@Serializable
sealed class ChatContent {

    companion object {
        fun createTextMessage(role: String, content: String): ChatMessage {
            return ChatMessage(
                role = role,
                content = listOf(TextContent(text = content))
            )
        }

        fun createImageMessage(role: String, imageUrl: String): ChatMessage {
            return ChatMessage(
                role = role,
                content = listOf(ImageContent(imageUrl = imageUrl))
            )
        }

        fun createMixedMessage(
            role: String,
            texts: List<String>,
            imageUrls: List<String>
        ): ChatMessage {
            val mixedContent = texts.map { TextContent(it) } + imageUrls.map { ImageContent(it) }
            return ChatMessage(
                role = role,
                content = mixedContent
            )
        }
    }

}

@Serializable
@SerialName("text")
data class TextContent(val text: String) : ChatContent()

@Serializable
@SerialName("image")
data class ImageContent(val imageUrl: String) : ChatContent()

@Serializable
data class ChatMessage(
    @SerialName("role") val role: String,
    @SerialName("content") val content: List<ChatContent>,
)

@Serializable
data class ChatMessageResponse(
    @SerialName("role") val role: String,
    @SerialName("content") val content: String,
)