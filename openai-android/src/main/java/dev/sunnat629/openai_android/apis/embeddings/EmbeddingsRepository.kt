package dev.sunnat629.openai_android.apis.embeddings

import dev.sunnat629.openai_android.networks.ApiResult
import dev.sunnat629.openai_android.networks.postRequest
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.util.InternalAPI

interface EmbeddingsRepository {

    // Embeddings Operations
    suspend fun createEmbedding(request: CreateEmbeddingsRequest): ApiResult<CreateEmbeddingsResponse>
}

class EmbeddingsRepositoryImpl(private val client: HttpClient): EmbeddingsRepository {
    private val baseUrl = "https://api.openai.com/v1/embeddings"

    override suspend fun createEmbedding(request: CreateEmbeddingsRequest): ApiResult<CreateEmbeddingsResponse> {
        return client.postRequest(baseUrl, request)
    }
}