/**
 * @author  Mohi Us Sunnat
 * @date    01.04.24
 * Copyright ©2024 Sunnat629.dev. All rights reserved.
 */

package dev.sunnat629.openai_android.models.messages

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Request model for creating a new message within a thread.
 *
 * @param thread The ID of the thread that the message is associated with.
 * @param content The content of the message.
 */
@Serializable
data class CreateMessageRequest(
    @SerialName("thread") val thread: String,
    @SerialName("content") val content: String
)

/**
 * Response model representing a message.
 *
 * @param id The unique identifier of the message.
 * @param objectContent The type of the object, typically "message".
 * @param thread The ID of the thread associated with the message.
 * @param content The content of the message.
 */
data class MessageResponse(
    @SerialName("id") val id: String,
    @SerialName("object") val objectContent: String,
    @SerialName("thread") val thread: String,
    @SerialName("content") val content: String
)