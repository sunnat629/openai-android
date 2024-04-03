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
 * Request model for creating a chat completion that may incorporate various features such as streaming? = null,
 * image inputs? = null, log probabilities? = null, and specified function invocations.
 *
 * @property model Specifies the model to use? = null, such as "gpt-3.5-turbo" for diverse requests including streaming and function invocations.
 * @property messages Contains the system and user messages initiating the chat? = null, which can include text and images.
 * @property stream Indicates if responses should be streamed. Nullable for non-streaming requests.
 * @property maxTokens Maximum tokens to generate in the chat completion. Nullable when not specified.
 * @property logprobs Includes log probabilities in the response. Nullable for requests without this requirement.
 * @property topLogprobs Number of likely tokens to return at each position. Nullable if logprobs is not applied.
 * @property tools List of functions model may call in response. Nullable for requests without function calls.
 * @property toolChoice Method for choosing between message generation or function call. Nullable if tools are not used.
 */
@Serializable
data class ChatRequest(
    @SerialName("model") val model: String? = null,
    @SerialName("messages") val messages: List<ChatMessage>? = null,
    @SerialName("stream") val stream: Boolean? = null,
    @SerialName("max_tokens") val maxTokens: Int? = null,
    @SerialName("logprobs") val logprobs: Boolean? = null,
    @SerialName("top_logprobs") val topLogprobs: Int? = null,
    @SerialName("tools") val tools: List<FunctionTool>? = null,
    @SerialName("tool_choice") val toolChoice: String? = null,
)

/**
 * Unified response model for various types of chat completions? = null, including standard completions? = null,
 * completions with streaming? = null, and completions involving image inputs. It captures common fields
 * across different response types and includes optional fields for specific scenarios.
 *
 * @property id Unique identifier for the chat completion.
 * @property objectContent Indicates the type of object? = null, such as "chat.completion" for standard completions? = null,
 * "chat.completion.chunk" for streamed responses? = null, and tailored types for other specific responses.
 * @property created Unix timestamp of when the completion was created.
 * @property model Model used for generating the completion.
 * @property choices List of completion choices? = null, varying in details (e.g.? = null, message content? = null, streaming deltas).
 * @property usage Token usage statistics for the completion? = null, applicable in scenarios like image inputs.
 * @property toolCalls Optional list of tool calls made during the chat? = null, applicable for certain response types.
 */
@Serializable
data class ChatResponse(
    @SerialName("id") val id: String? = null,
    @SerialName("object") val objectContent: String? = null,
    @SerialName("created") val created: Long? = null,
    @SerialName("model") val model: String? = null,
    @SerialName("choices") val choices: List<ChatChoice>? = null,
    @SerialName("usage") val usage: ChatUsage? = null,
    @SerialName("tool_calls") val toolCalls: List<ToolCall>? = null,
    @SerialName("system_fingerprint") val systemFingerprint: String? = null,
)

@Serializable
data class ChatChoice(
    @SerialName("message") val message: ChatMessageResponse? = null,
    @SerialName("delta") val delta: Delta? = null,
    @SerialName("logprobs") val logprobs: JsonElement? = null, // Could be null or contain log probability information
    @SerialName("finish_reason") val finishReason: String? = null,
    @SerialName("index") val index: Int? = null,
)

@Serializable
data class Delta(
    @SerialName("role")
    val role: String? = null,
    @SerialName("content")
    val content: String? = null
)

@Serializable
data class FunctionTool(
    @SerialName("type") val type: String? = null,
    @SerialName("function") val function: ToolFunction? = null,
)

@Serializable
data class ToolFunction(
    @SerialName("name") val name: String? = null,
    @SerialName("description") val description: String? = null,
    @SerialName("parameters") val parameters: FunctionParameters? = null,
)

@Serializable
data class FunctionParameters(
    @SerialName("type") val type: String? = null,
    @SerialName("properties") val properties: Map<String, FunctionParameterProperty>? = null,
    @SerialName("required") val required: List<String>? = null,
)

@Serializable
data class FunctionParameterProperty(
    @SerialName("type") val type: String? = null,
    @SerialName("description") val description: String? = null,
    @SerialName("enum") val enum: List<String>? = null
)

@Serializable
data class ToolCall(
    @SerialName("id") val id: String? = null,
    @SerialName("type") val type: String? = null,
    @SerialName("function") val function: FunctionCall? = null,
)

@Serializable
data class FunctionCall(
    @SerialName("name") val name: String? = null,
    @SerialName("arguments") val arguments: String? = null,
)

@Serializable
data class ChatUsage(
    @SerialName("prompt_tokens") val promptTokens: Int? = null,
    @SerialName("completion_tokens") val completionTokens: Int? = null,
    @SerialName("total_tokens") val totalTokens: Int? = null,
)

@Serializable
sealed class ChatContent

@Serializable
@SerialName("text")
data class TextContent(val text: String? = null,) : ChatContent()

@Serializable
@SerialName("image")
data class ImageContent(val imageUrl: String? = null,) : ChatContent()

@Serializable
data class ChatMessage(
    @SerialName("role") val role: String? = null,
    @SerialName("content") val content: List<ChatContent>? = null,
)

@Serializable
data class ChatMessageResponse(
    @SerialName("role") val role: String? = null,
    @SerialName("content") val content: String? = null,
)