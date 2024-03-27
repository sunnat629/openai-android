package dev.sunnat629.openai_android.apis.chats

import io.ktor.client.HttpClient
import io.ktor.client.request.post

data class MockResponse(val result: String)

interface ChatsRepository {

    // Chat Operations
    suspend fun createChatCompletion(): MockResponse
}

class ChatRepositoryImpl(private val client: HttpClient) : ChatsRepository {
    private val baseUrl = "https://api.openai.com/v1/chat"

    override suspend fun createChatCompletion(): MockResponse {
        client.post("$baseUrl/completions") { /* Request body */ }
    }
}