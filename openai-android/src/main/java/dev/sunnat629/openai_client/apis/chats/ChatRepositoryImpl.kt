/**
 * @author  Mohi Us Sunnat
 * @date    01.04.24
 * Copyright ©2024 Sunnat629.dev. All rights reserved.
 */

package dev.sunnat629.openai_client.apis.chats

import android.util.Log
import dev.sunnat629.openai_client.models.chats.ChatRequest
import dev.sunnat629.openai_client.models.chats.ChatResponse
import dev.sunnat629.openai_client.networks.postRequest
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.accept
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import io.ktor.utils.io.ByteReadChannel
import io.ktor.utils.io.cancel
import io.ktor.utils.io.readUTF8Line
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.Json

class ChatRepositoryImpl(private val client: HttpClient) : ChatRepository {

    private val baseUrl = "https://api.openai.com/v1/chat/completions"

    override suspend fun createChat(request: ChatRequest): ChatResponse {
        return client.postRequest<ChatResponse>(baseUrl, request)
    }

    private val jsonLenient = Json {
        isLenient = true
        ignoreUnknownKeys = true
    }

    override fun createChatSteam(request: ChatRequest, delay: Long): Flow<ChatResponse> {
        return flow {
            val response: HttpResponse =
                client.post(baseUrl) {
                    this.setBody(request)
                    contentType(ContentType.Application.Json)
                    accept(ContentType.Text.EventStream)
                    headers {
                        append(HttpHeaders.CacheControl, "no-cache")
                        append(HttpHeaders.Connection, "keep-alive")
                    }
                }

            val channel: ByteReadChannel = response.body()
            try {
                // Process the channel line by line
                while (!channel.isClosedForRead) {
                    val line: String = channel.readUTF8Line() ?: break
                    val value: ChatResponse = when {
                        line.contains("[DONE]") -> break
                        line.startsWith("data:") ->
                            jsonLenient.decodeFromString<ChatResponse>(
                                line.removePrefix("data:")
                            )
                        else -> continue
                    }
                    delay(delay)
                    emit(value)
                }
            } catch (e: Exception) {
                throw e
            } finally {
                channel.cancel() // Important to cancel the channel to avoid resource leaks
            }
        }
    }

    override suspend fun createChatFunction(request: ChatRequest): ChatResponse {
        return client.postRequest(baseUrl, request)
    }

    override suspend fun createChatLogprobs(request: ChatRequest): ChatResponse {
        return client.postRequest(baseUrl, request)
    }
}