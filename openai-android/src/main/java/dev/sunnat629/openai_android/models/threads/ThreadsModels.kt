/**
 * @author  Mohi Us Sunnat
 * @date    01.04.24
 * Copyright ©2024 Sunnat629.dev. All rights reserved.
 */

package dev.sunnat629.openai_android.models.threads

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Request model for creating a new thread.
 *
 * @param assistant The ID of the assistant that the thread is associated with.
 * @param title The title of the thread.
 */
@Serializable
data class CreateThreadRequest(
    @SerialName("assistant") val assistant: String,
    @SerialName("title") val title: String
)

/**
 * Response model representing a thread.
 *
 * @param id The unique identifier of the thread.
 * @param objectContent The type of the object, typically "thread".
 * @param assistant The ID of the assistant associated with the thread.
 * @param title The title of the thread.
 */
@Serializable
data class ThreadResponse(
    @SerialName("id") val id: String,
    @SerialName("object") val objectContent: String,
    @SerialName("assistant") val assistant: String,
    @SerialName("title") val title: String
)