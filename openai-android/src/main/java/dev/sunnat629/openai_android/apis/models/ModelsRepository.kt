package dev.sunnat629.openai_android.apis.models

import dev.sunnat629.openai_android.models.models.ListModelsResponse
import dev.sunnat629.openai_android.models.models.ModelResponse
import dev.sunnat629.openai_android.networks.ApiResult
import dev.sunnat629.openai_android.networks.getRequest
import io.ktor.client.HttpClient

interface ModelsRepository {
    suspend fun listModels(): ApiResult<ListModelsResponse>
    suspend fun retrieveModel(modelId: String): ApiResult<ModelResponse>
}

class ModelsRepositoryImpl(private val httpClient: HttpClient) : ModelsRepository {
    private val baseUrl = "https://api.openai.com/v1/models"

    override suspend fun listModels(): ApiResult<ListModelsResponse> {
        return httpClient.getRequest(
            url = baseUrl
        )
    }

    override suspend fun retrieveModel(modelId: String): ApiResult<ModelResponse> {
        return httpClient.getRequest(
            url = "$baseUrl/$modelId"
        )
    }
}
