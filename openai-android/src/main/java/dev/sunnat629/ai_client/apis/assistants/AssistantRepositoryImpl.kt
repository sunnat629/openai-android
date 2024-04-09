/**
 * @author  Mohi Us Sunnat
 * @date    01.04.24
 * Copyright ©2024 Sunnat629.dev. All rights reserved.
 */

package dev.sunnat629.ai_client.apis.assistants

import dev.sunnat629.ai_client.models.assistants.AssistantResponse
import dev.sunnat629.ai_client.models.assistants.CreateAssistantRequest
import dev.sunnat629.ai_client.networks.deleteRequest
import dev.sunnat629.ai_client.networks.getRequest
import dev.sunnat629.ai_client.networks.patchRequest
import dev.sunnat629.ai_client.networks.postRequest
import io.ktor.client.HttpClient


class AssistantRepositoryImpl(private val httpClient: HttpClient) : AssistantRepository {

    private val baseUrl = "https://api.openai.com/v1/assistants"

    override suspend fun createAssistant(request: CreateAssistantRequest): AssistantResponse {
        return httpClient.postRequest(
            url = baseUrl,
            request = request
        )
    }

    override suspend fun listAssistants(): List<AssistantResponse> {
        return httpClient.getRequest(
            url = baseUrl
        )
    }

    override suspend fun retrieveAssistant(assistantId: String): AssistantResponse {
        return httpClient.getRequest(
            url = "$baseUrl/$assistantId"
        )
    }

    override suspend fun updateAssistant(
        assistantId: String,
        request: CreateAssistantRequest
    ): AssistantResponse {
        return httpClient.patchRequest(
            url = "$baseUrl/$assistantId",
            request = request
        )
    }

    override suspend fun deleteAssistant(assistantId: String): Unit {
        return httpClient.deleteRequest(
            url = "$baseUrl/$assistantId"
        )
    }
}