/**
 * @author  Mohi Us Sunnat
 * @date    01.04.24
 * Copyright ©2024 Sunnat629.dev. All rights reserved.
 */

package dev.sunnat629.openai_client.apis.chats

import dev.sunnat629.openai_client.models.chats.ChatRequest
import dev.sunnat629.openai_client.models.chats.ChatResponse
import dev.sunnat629.openai_client.networks.ApiResult
import dev.sunnat629.openai_client.networks.postRequest
import io.ktor.client.HttpClient

class ChatRepositoryImpl(private val client: HttpClient) : ChatRepository {

    private val baseUrl = "https://api.openai.com/v1/chat/completions"

    override suspend fun chat(request: ChatRequest): ApiResult<ChatResponse> {
        return client.postRequest(baseUrl, request)
    }
}