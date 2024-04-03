/**
 * @author  Mohi Us Sunnat
 * @date    01.04.24
 * Copyright ©2024 Sunnat629.dev. All rights reserved.
 */

package dev.sunnat629.openai_client.apis.chats

import android.util.Log
import dev.sunnat629.openai_client.clients.chats.AAA
import dev.sunnat629.openai_client.di.JsonUtils
import dev.sunnat629.openai_client.models.chats.ChatRequest
import dev.sunnat629.openai_client.models.chats.ChatResponse
import dev.sunnat629.openai_client.networks.ApiResult
import dev.sunnat629.openai_client.networks.postRequest
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.accept
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.request
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.client.utils.EmptyContent.headers
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import io.ktor.utils.io.ByteReadChannel
import io.ktor.utils.io.cancel
import io.ktor.utils.io.readUTF8Line
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import org.json.JSONException

class ChatRepositoryImpl(private val client: HttpClient) : ChatRepository {

    private val baseUrl = "https://api.openai.com/v1/chat/completions"

    override suspend fun chatText(request: ChatRequest): ApiResult<ChatResponse> {
        return client.postRequest(baseUrl, request)
    }

    override suspend fun chatImage(request: ChatRequest): ApiResult<ChatResponse> {
        return client.postRequest(baseUrl, request)
    }
    internal val jsonLenient = Json {
        isLenient = true
        ignoreUnknownKeys = true
    }

    override suspend fun chatSteam(request: ChatRequest): Flow<ApiResult<ChatResponse>> {
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
                    val line: String = channel.readUTF8Line() ?: break  // Reads each line from the stream


                   val value: ChatResponse = when {
                        line.startsWith(STREAM_END_TOKEN) -> break
                        line.startsWith(STREAM_PREFIX) ->
                            jsonLenient.decodeFromString<ChatResponse>(
                                line.removePrefix(
                                    STREAM_PREFIX
                                )
                            )
                        else -> continue
                    }
                    Log.w("ASDF", value.toString())
                    emit(ApiResult.Success(value))
                }
            } catch (e: Exception) {
                ApiResult.Failure(e)
            }
            finally {
                channel.cancel() // Important to cancel the channel to avoid resource leaks
            }

        }
    }


    private val STREAM_PREFIX = "data:"
    private val STREAM_END_TOKEN = "$STREAM_PREFIX [DONE]"


    override suspend fun chatFunction(request: ChatRequest): ApiResult<ChatResponse> {
        return client.postRequest(baseUrl, request)
    }

    override suspend fun chatLogprobs(request: ChatRequest): ApiResult<ChatResponse> {
        return client.postRequest(baseUrl, request)
    }
}