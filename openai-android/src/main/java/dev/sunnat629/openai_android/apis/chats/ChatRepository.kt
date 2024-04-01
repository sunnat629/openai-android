/**
 * @author  Mohi Us Sunnat
 * @date    01.04.24
 * Copyright ©2024 Sunnat629.dev. All rights reserved.
 */

package dev.sunnat629.openai_android.apis.chats

import dev.sunnat629.openai_android.models.chats.ChatImageFunctionsRequest
import dev.sunnat629.openai_android.models.chats.ChatImageInputRequest
import dev.sunnat629.openai_android.models.chats.ChatImageInputResponse
import dev.sunnat629.openai_android.models.chats.ChatImageLogprobsRequest
import dev.sunnat629.openai_android.models.chats.ChatImageStreamingRequest
import dev.sunnat629.openai_android.models.chats.ChatImageStreamingResponse
import dev.sunnat629.openai_android.networks.ApiResult

interface ChatRepository {

    suspend fun submitChatCompletion(request: ChatImageInputRequest): ApiResult<ChatImageInputResponse>
    suspend fun submitChatCompletionWithFunctions(request: ChatImageFunctionsRequest): ApiResult<ChatImageInputResponse>
    suspend fun submitChatCompletionWithLogprobs(request: ChatImageLogprobsRequest): ApiResult<ChatImageInputResponse>
    suspend fun streamChatCompletion(request: ChatImageStreamingRequest): ApiResult<ChatImageStreamingResponse>
}