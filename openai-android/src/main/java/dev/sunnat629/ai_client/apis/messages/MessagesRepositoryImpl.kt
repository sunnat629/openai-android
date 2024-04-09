/**
 * @author  Mohi Us Sunnat
 * @date    01.04.24
 * Copyright ©2024 Sunnat629.dev. All rights reserved.
 */

package dev.sunnat629.ai_client.apis.messages

import dev.sunnat629.ai_client.models.messages.CreateMessageRequest
import dev.sunnat629.ai_client.models.messages.ListMessageFilesResponse
import dev.sunnat629.ai_client.models.messages.MessageFileDetails
import dev.sunnat629.ai_client.models.messages.MessageResponse
import dev.sunnat629.ai_client.networks.getRequest
import dev.sunnat629.ai_client.networks.postRequest
import io.ktor.client.HttpClient

class MessageRepositoryImpl(private val httpClient: HttpClient) : MessageRepository {

    private val baseUrl = "https://api.openai.com/v1/threads"

    override suspend fun createMessage(
        threadId: String,
        request: CreateMessageRequest
    ): MessageResponse {
        return httpClient.postRequest(
            url = "$baseUrl/$threadId/messages",
            request = request
        )
    }

    override suspend fun listMessages(threadId: String): List<MessageResponse> {
        return httpClient.getRequest(
            url = "$baseUrl/$threadId/messages"
        )
    }

    override suspend fun retrieveMessage(
        threadId: String,
        messageId: String
    ): MessageResponse {
        return httpClient.getRequest(
            url = "$baseUrl/$threadId/messages/$messageId"
        )
    }

    override suspend fun listMessageFiles(
        threadId: String,
        messageId: String
    ): ListMessageFilesResponse {
        TODO("Not yet implemented")
    }

    override suspend fun retrieveMessageFile(
        threadId: String,
        messageId: String,
        fileId: String
    ): MessageFileDetails {
        TODO("Not yet implemented")
    }
}