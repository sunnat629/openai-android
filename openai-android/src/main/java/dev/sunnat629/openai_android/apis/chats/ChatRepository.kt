package dev.sunnat629.openai_android.apis.chats

import dev.sunnat629.openai_android.networks.ApiResult
import dev.sunnat629.openai_android.networks.postRequest
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.call.receive
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.request
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import kotlinx.coroutines.flow.Flow

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