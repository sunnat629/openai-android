package dev.sunnat629.openai_android.apis.threadsMessages

import dev.sunnat629.openai_android.models.threadsMessages.CreateMessageRequest
import dev.sunnat629.openai_android.models.threadsMessages.MessageResponse
import dev.sunnat629.openai_android.networks.ApiResult
import dev.sunnat629.openai_android.networks.getRequest
import dev.sunnat629.openai_android.networks.postRequest
import io.ktor.client.HttpClient


// MessageRepository.kt
interface MessageRepository {
    /** Creates a new message in a thread */
    suspend fun createMessage(threadId: String, request: CreateMessageRequest): ApiResult<MessageResponse>
    /** Lists all messages in a thread */
    suspend fun listMessages(threadId: String): ApiResult<List<MessageResponse>>
    /** Retrieves a message by ID */
    suspend fun retrieveMessage(threadId: String, messageId: String): ApiResult<MessageResponse>
}

class MessageRepositoryImpl(private val httpClient: HttpClient) : MessageRepository {
    override suspend fun createMessage(threadId: String, request: CreateMessageRequest): ApiResult<MessageResponse> {
        return httpClient.postRequest(
            url = "https://api.openai.com/v1/threads/$threadId/messages",
            request = request
        )
    }

    override suspend fun listMessages(threadId: String): ApiResult<List<MessageResponse>> {
        return httpClient.getRequest(
            url = "https://api.openai.com/v1/threads/$threadId/messages"
        )
    }

    override suspend fun retrieveMessage(threadId: String, messageId: String): ApiResult<MessageResponse> {
        return httpClient.getRequest(
            url = "https://api.openai.com/v1/threads/$threadId/messages/$messageId"
        )
    }
}
