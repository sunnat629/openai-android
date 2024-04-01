package dev.sunnat629.openai_android.apis.embeddings

import dev.sunnat629.openai_android.models.embeddings.CreateEmbeddingsRequest
import dev.sunnat629.openai_android.models.embeddings.CreateEmbeddingsResponse
import dev.sunnat629.openai_android.networks.ApiResult
import dev.sunnat629.openai_android.networks.postRequest
import io.ktor.client.HttpClient

interface EmbeddingsRepository {

    // Embeddings Operations
    suspend fun createEmbedding(request: CreateEmbeddingsRequest): ApiResult<CreateEmbeddingsResponse>
}

class EmbeddingsRepositoryImpl(private val client: HttpClient) : EmbeddingsRepository {
    private val baseUrl = "https://api.openai.com/v1/embeddings"

    override suspend fun createEmbedding(request: CreateEmbeddingsRequest): ApiResult<CreateEmbeddingsResponse> {
        return client.postRequest(baseUrl, request)
    }
}