package dev.sunnat629.openai_android.apis.embeddings

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.util.InternalAPI

data class MockResponse(val result: String)

interface EmbeddingsRepository {

    // Embeddings Operations
    suspend fun createEmbedding(request: CreateEmbeddingsRequest): CreateEmbeddingsResponse
}

class EmbeddingsRepositoryImpl(private val client: HttpClient): EmbeddingsRepository {
    private val baseUrl = "https://api.openai.com/v1/embeddings"

    override suspend fun createEmbedding(request: CreateEmbeddingsRequest): CreateEmbeddingsResponse {
        val response: HttpResponse = client.post(baseUrl) {
            contentType(ContentType.Application.Json)
            setBody(request)
        }
        // Explicitly specify the expected response type
        return response.body<CreateEmbeddingsResponse>()
    }
}