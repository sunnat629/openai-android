package dev.sunnat629.openai_android.apis.models

import io.ktor.client.HttpClient
import io.ktor.client.request.delete
import io.ktor.client.request.get

data class MockResponse(val result: String)

interface ModelsRepository {

    // Models Operations
    suspend fun listModels(): MockResponse
    suspend fun getModelDetails(modelId: String): MockResponse
    suspend fun deleteModel(modelId: String): MockResponse
}

class ModelsRepositoryImpl(private val client: HttpClient): ModelsRepository {
    private val baseUrl = "https://api.openai.com/v1/models"

   override suspend fun listModels(): MockResponse = client.get(baseUrl) { }
    override suspend fun getModelDetails(modelId: String): MockResponse = client.get("$baseUrl/$modelId") { }
    override suspend fun deleteModel(modelId: String): MockResponse = client.delete("$baseUrl/$modelId") { }
}
