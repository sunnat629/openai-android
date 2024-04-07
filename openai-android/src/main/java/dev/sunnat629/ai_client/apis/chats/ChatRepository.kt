/**
 * @author  Mohi Us Sunnat
 * @date    01.04.24
 * Copyright ©2024 Sunnat629.dev. All rights reserved.
 */

package dev.sunnat629.ai_client.apis.chats

import dev.sunnat629.ai_client.models.chats.ChatRequest
import dev.sunnat629.ai_client.models.chats.ChatResponse
import kotlinx.coroutines.flow.Flow

interface ChatRepository {

    suspend fun createChat(request: ChatRequest): ChatResponse
    fun createChatSteam(request: ChatRequest, delay: Long): Flow<ChatResponse>
    suspend fun createChatFunction(request: ChatRequest): ChatResponse
    suspend fun createChatLogprobs(request: ChatRequest): ChatResponse
}