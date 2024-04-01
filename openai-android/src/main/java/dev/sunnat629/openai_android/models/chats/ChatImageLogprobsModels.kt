package dev.sunnat629.openai_android.models.chats

import dev.sunnat629.openai_android.models.chats.ChatMessage
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Request model for submitting a chat completion with log probabilities.
 *
 * @param model Specifies the model to use, such as "gpt-3.5-turbo".
 * @param messages A list containing the user's message.
 * @param logprobs Indicates that log probabilities should be included in the response.
 * @param topLogprobs Specifies the number of most likely tokens to return at each token position.
 */
@Serializable
data class ChatImageLogprobsRequest(
    @SerialName("model") val model: String,
    @SerialName("messages") val messages: List<ChatMessage>,
    @SerialName("logprobs") val logprobs: Boolean,
    @SerialName("top_logprobs") val topLogprobs: Int
)
