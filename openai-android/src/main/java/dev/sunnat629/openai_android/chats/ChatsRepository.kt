package dev.sunnat629.openai_android.chats

data class MockResponse(val result: String)

interface ChatsRepository {

     // Chat Operations
    suspend fun createChatCompletion(): MockResponse
}