/**
 * @author  Mohi Us Sunnat
 * @date    01.04.24
 * Copyright ©2024 Sunnat629.dev. All rights reserved.
 */

package dev.sunnat629.openai_android.apis.assistants

import dev.sunnat629.openai_android.models.assistants.AssistantResponse
import dev.sunnat629.openai_android.models.assistants.CreateAssistantRequest
import dev.sunnat629.openai_android.networks.ApiResult
import dev.sunnat629.openai_android.networks.deleteRequest
import dev.sunnat629.openai_android.networks.getRequest
import dev.sunnat629.openai_android.networks.patchRequest
import dev.sunnat629.openai_android.networks.postRequest
import io.ktor.client.HttpClient


class AssistantRepositoryImpl(private val httpClient: HttpClient) : AssistantRepository {

    private val baseUrl = "https://api.openai.com/v1/assistants"

    override suspend fun createAssistant(request: CreateAssistantRequest): ApiResult<AssistantResponse> {
        return httpClient.postRequest(
            url = baseUrl,
            request = request
        )
    }

    override suspend fun listAssistants(): ApiResult<List<AssistantResponse>> {
        return httpClient.getRequest(
            url = baseUrl
        )
    }

    override suspend fun retrieveAssistant(assistantId: String): ApiResult<AssistantResponse> {
        return httpClient.getRequest(
            url = "$baseUrl/$assistantId"
        )
    }

    override suspend fun updateAssistant(
        assistantId: String,
        request: CreateAssistantRequest
    ): ApiResult<AssistantResponse> {
        return httpClient.patchRequest(
            url = "$baseUrl/$assistantId",
            request = request
        )
    }

    override suspend fun deleteAssistant(assistantId: String): ApiResult<Unit> {
        return httpClient.deleteRequest(
            url = "$baseUrl/$assistantId"
        )
    }
}