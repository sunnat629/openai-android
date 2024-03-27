package dev.sunnat629.openai_android.apis.embeddings

import io.ktor.client.HttpClient
import io.ktor.client.request.post

data class MockResponse(val result: String)

interface EmbeddingsRepository {

    // Embeddings Operations
    suspend fun createEmbedding(): MockResponse
}

class EmbeddingsRepositoryImpl(private val client: HttpClient): EmbeddingsRepository {
    private val baseUrl = "https://api.openai.com/v1/embeddings"

    override suspend fun createEmbedding(): MockResponse = client.post(baseUrl) { /* Request body */ }
}