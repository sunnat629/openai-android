/**
 * @author  Mohi Us Sunnat
 * @date    01.04.24
 * Copyright ©2024 Sunnat629.dev. All rights reserved.
 */

package dev.sunnat629.openai_android.apis.chats

import dev.sunnat629.openai_android.models.chats.ChatRequest
import dev.sunnat629.openai_android.models.chats.ChatResponse
import dev.sunnat629.openai_android.networks.ApiResult

interface ChatRepository {

    suspend fun chat(request: ChatRequest): ApiResult<ChatResponse>
}