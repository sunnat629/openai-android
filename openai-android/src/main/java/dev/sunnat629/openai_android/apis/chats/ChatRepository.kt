package dev.sunnat629.openai_android.apis.chats

import dev.sunnat629.openai_android.models.chats.ChatImageFunctionsRequest
import dev.sunnat629.openai_android.models.chats.ChatImageInputRequest
import dev.sunnat629.openai_android.models.chats.ChatImageInputResponse
import dev.sunnat629.openai_android.models.chats.ChatImageStreamingRequest
import dev.sunnat629.openai_android.models.chats.ChatImageStreamingResponse
import dev.sunnat629.openai_android.networks.ApiResult
import dev.sunnat629.openai_android.networks.postRequest
import io.ktor.client.HttpClient

data class MockResponse(val result: String)

interface ChatRepository {

    suspend fun submitChatCompletion(request: ChatImageInputRequest): ApiResult<ChatImageInputResponse>
    suspend fun submitChatCompletionWithFunctions(request: ChatImageFunctionsRequest): ApiResult<ChatImageInputResponse>
    suspend fun submitChatCompletionWithLogprobs(request: ChatImageLogprobsRequest): ApiResult<ChatImageInputResponse>
    suspend fun streamChatCompletion(request: ChatImageStreamingRequest): ApiResult<ChatImageStreamingResponse>
}


class ChatRepositoryImpl(private val client: HttpClient) : ChatRepository {
    private val baseUrl = "https://api.openai.com/v1/chat/completions"

    override suspend fun submitChatCompletion(request: ChatImageInputRequest): ApiResult<ChatImageInputResponse> {
        return client.postRequest(baseUrl, request)
    }

    override suspend fun submitChatCompletionWithFunctions(request: ChatImageFunctionsRequest): ApiResult<ChatImageInputResponse> {
        return client.postRequest(baseUrl, request)
    }

    override suspend fun submitChatCompletionWithLogprobs(request: ChatImageLogprobsRequest): ApiResult<ChatImageInputResponse> {
        return client.postRequest(baseUrl, request)
    }

    override suspend fun streamChatCompletion(request: ChatImageStreamingRequest): ApiResult<ChatImageStreamingResponse> {
        return client.postRequest(baseUrl, request)
    }
}