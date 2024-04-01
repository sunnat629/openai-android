package dev.sunnat629.openai_android.apis.assistants

import dev.sunnat629.openai_android.models.assistants.AssistantResponse
import dev.sunnat629.openai_android.models.assistants.CreateAssistantRequest
import dev.sunnat629.openai_android.networks.ApiResult
import dev.sunnat629.openai_android.networks.deleteRequest
import dev.sunnat629.openai_android.networks.getRequest
import dev.sunnat629.openai_android.networks.patchRequest
import dev.sunnat629.openai_android.networks.postRequest
import io.ktor.client.HttpClient
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// AssistantRepository.kt
interface AssistantRepository {
    /** Creates a new assistant */
    suspend fun createAssistant(request: CreateAssistantRequest): ApiResult<AssistantResponse>
    /** Lists all assistants */
    suspend fun listAssistants(): ApiResult<List<AssistantResponse>>
    /** Retrieves an assistant by ID */
    suspend fun retrieveAssistant(assistantId: String): ApiResult<AssistantResponse>
    /** Updates an assistant */
    suspend fun updateAssistant(assistantId: String, request: CreateAssistantRequest): ApiResult<AssistantResponse>
    /** Deletes an assistant */
    suspend fun deleteAssistant(assistantId: String): ApiResult<Unit>
}

class AssistantRepositoryImpl(private val httpClient: HttpClient) : AssistantRepository {

    override suspend fun createAssistant(request: CreateAssistantRequest): ApiResult<AssistantResponse> {
        return httpClient.postRequest(
            url = "https://api.openai.com/v1/assistants",
            request = request
        )
    }

    override suspend fun listAssistants(): ApiResult<List<AssistantResponse>> {
        return httpClient.getRequest(
            url = "https://api.openai.com/v1/assistants"
        )
    }

    override suspend fun retrieveAssistant(assistantId: String): ApiResult<AssistantResponse> {
        return httpClient.getRequest(
            url = "https://api.openai.com/v1/assistants/$assistantId"
        )
    }

    override suspend fun updateAssistant(assistantId: String, request: CreateAssistantRequest): ApiResult<AssistantResponse> {
        return httpClient.patchRequest(
            url = "https://api.openai.com/v1/assistants/$assistantId",
            request = request
        )
    }

    override suspend fun deleteAssistant(assistantId: String): ApiResult<Unit> {
        return httpClient.deleteRequest(
            url = "https://api.openai.com/v1/assistants/$assistantId"
        )
    }
}
