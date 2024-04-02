/**
 * @author  Mohi Us Sunnat
 * @date    01.04.24
 * Copyright ©2024 Sunnat629.dev. All rights reserved.
 */

package dev.sunnat629.openai_client.models.assistants

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Request model for creating a new assistant.
 *
 * @param model The model to be used by the assistant for generating responses.
 * @param name A human-readable name for the assistant.
 * @param description An optional description of the assistant's purpose or functionality.
 */
@Serializable
data class CreateAssistantRequest(
    @SerialName("model") val model: String,
    @SerialName("name") val name: String,
    @SerialName("description") val description: String? = null
)

/**
 * Response model representing an assistant.
 *
 * @param id The unique identifier of the assistant.
 * @param objectContent The type of the object, typically "assistant".
 * @param name The name of the assistant.
 * @param model The model used by the assistant.
 * @param description A description of the assistant's purpose or functionality.
 */
@Serializable
data class AssistantResponse(
    @SerialName("id") val id: String,
    @SerialName("object") val objectContent: String,
    @SerialName("name") val name: String,
    @SerialName("model") val model: String,
    @SerialName("description") val description: String? = null
)

