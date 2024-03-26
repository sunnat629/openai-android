package dev.sunnat629.openai_android.embeddings

data class MockResponse(val result: String)

interface EmbeddingsRepository {

    // Embeddings Operations
    suspend fun createEmbedding(): MockResponse
}