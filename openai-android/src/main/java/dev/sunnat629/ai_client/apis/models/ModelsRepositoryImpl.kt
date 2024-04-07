/**
 * @author  Mohi Us Sunnat
 * @date    01.04.24
 * Copyright ©2024 Sunnat629.dev. All rights reserved.
 */

package dev.sunnat629.ai_client.apis.models

import dev.sunnat629.ai_client.models.models.ListModelsResponse
import dev.sunnat629.ai_client.models.models.ModelResponse
import dev.sunnat629.ai_client.networks.getRequest
import io.ktor.client.HttpClient

class ModelsRepositoryImpl(private val httpClient: HttpClient) : ModelsRepository {
    private val baseUrl = "https://api.openai.com/v1/models"

    override suspend fun listModels(): ListModelsResponse {
        return httpClient.getRequest(url = baseUrl)
    }

    override suspend fun retrieveModel(modelId: String): ModelResponse {
        return httpClient.getRequest(url = "$baseUrl/$modelId")
    }
}
