/**
 * @author  Mohi Us Sunnat
 * @date    01.04.24
 * Copyright ©2024 Sunnat629.dev. All rights reserved.
 */

package dev.sunnat629.openai_android.apis.messages

import dev.sunnat629.openai_android.models.messages.CreateMessageRequest
import dev.sunnat629.openai_android.models.messages.MessageResponse
import dev.sunnat629.openai_android.networks.ApiResult
import dev.sunnat629.openai_android.networks.getRequest
import dev.sunnat629.openai_android.networks.postRequest
import io.ktor.client.HttpClient


// MessageRepository.kt
interface MessageRepository {
    /** Creates a new message in a thread */
    suspend fun createMessage(
        threadId: String,
        request: CreateMessageRequest
    ): ApiResult<MessageResponse>

    /** Lists all messages in a thread */
    suspend fun listMessages(threadId: String): ApiResult<List<MessageResponse>>

    /** Retrieves a message by ID */
    suspend fun retrieveMessage(threadId: String, messageId: String): ApiResult<MessageResponse>
}