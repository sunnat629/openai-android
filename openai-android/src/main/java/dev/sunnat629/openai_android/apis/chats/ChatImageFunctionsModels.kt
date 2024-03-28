package dev.sunnat629.openai_android.apis.chats

import kotlinx.serialization.*

/**
 * Request model for submitting a chat completion that may invoke a specified function.
 *
 * @param model Specifies the model to use, such as "gpt-3.5-turbo" for function invocation.
 * @param messages A list containing the user's message prompting a potential function call.
 * @param tools A list of functions that the model may call in response to the user's message.
 * @param toolChoice Specifies how the model should choose between generating a message or calling a function.
 */
@Serializable
data class ChatImageFunctionsRequest(
    @SerialName("model") val model: String,
    @SerialName("messages") val messages: List<ChatMessage>,
    @SerialName("tools") val tools: List<FunctionTool>,
    @SerialName("tool_choice") val toolChoice: String
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
