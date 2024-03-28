package dev.sunnat629.openai_android.apis.assistants

import io.ktor.client.HttpClient
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MockResponse(@SerialName val result: String)

interface AssistantsRepository {

    // Assistants Operations
    suspend fun createAssistant(): MockResponse
    suspend fun listAssistants(): MockResponse
    suspend fun getAssistantDetails(assistantId: String): MockResponse
    suspend fun deleteAssistant(assistantId: String): MockResponse
}

class AssistantsRepositoryImpl(private val client: HttpClient): AssistantsRepository {
    private val baseUrl = "https://api.openai.com/v1/assistants"

  override suspend fun createAssistant(): MockResponse = client.post(baseUrl) { /* Request body */ }
    override  suspend fun listAssistants(): MockResponse = client.get(baseUrl) { }
    override  suspend fun getAssistantDetails(assistantId: String): MockResponse =
        client.get("$baseUrl/$assistantId") { }

    override  suspend fun deleteAssistant(assistantId: String): MockResponse =
        client.delete("$baseUrl/$assistantId") { }
}
