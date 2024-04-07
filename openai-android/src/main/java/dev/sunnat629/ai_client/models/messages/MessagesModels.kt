/**
 * @author  Mohi Us Sunnat
 * @date    01.04.24
 * Copyright ©2024 Sunnat629.dev. All rights reserved.
 */

package dev.sunnat629.ai_client.models.messages

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

/**
 * Response model for listing files associated with a specific message.
 */
@Serializable
data class ListMessageFilesResponse(
    @SerialName("data") val data: List<MessageFileDetails>
)

/**
 * Details of a file associated with a message.
 */
@Serializable
data class MessageFileDetails(
    @SerialName("id") val id: String,
    @SerialName("name") val name: String,
    @SerialName("created_at") val createdAt: String, // Assuming ISO 8601 format
    @SerialName("file_type") val fileType: String
)
