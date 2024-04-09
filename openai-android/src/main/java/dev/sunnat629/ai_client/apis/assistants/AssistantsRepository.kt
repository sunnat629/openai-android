/**
 * @author  Mohi Us Sunnat
 * @date    01.04.24
 * Copyright ©2024 Sunnat629.dev. All rights reserved.
 */

package dev.sunnat629.ai_client.apis.assistants

import dev.sunnat629.ai_client.models.assistants.AssistantResponse
import dev.sunnat629.ai_client.models.assistants.CreateAssistantRequest

// AssistantRepository.kt
interface AssistantRepository {
    /** Creates a new assistant */
    suspend fun createAssistant(request: CreateAssistantRequest): AssistantResponse

    /** Lists all assistants */
    suspend fun listAssistants(): List<AssistantResponse>

    /** Retrieves an assistant by ID */
    suspend fun retrieveAssistant(assistantId: String): AssistantResponse

    /** Updates an assistant */
    suspend fun updateAssistant(
        assistantId: String,
        request: CreateAssistantRequest
    ): AssistantResponse

    /** Deletes an assistant */
    suspend fun deleteAssistant(assistantId: String): Unit
}