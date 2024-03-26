package dev.sunnat629.openai_android.threadsMessages

data class MockResponse(val result: String)

interface OpenAIRepository {

    // Threads and Messages Operations
    suspend fun createThread(assistantId: String): MockResponse
    suspend fun getThreadDetails(assistantId: String, threadId: String): MockResponse
    suspend fun createMessage(assistantId: String, threadId: String): MockResponse
    suspend fun getMessageDetails(assistantId: String, threadId: String, messageId: String): MockResponse
}