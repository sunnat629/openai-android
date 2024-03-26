package dev.sunnat629.openai_android.assistants

data class MockResponse(val result: String)

interface AssistantsRepository {

    // Assistants Operations
    suspend fun createAssistant(): MockResponse
    suspend fun listAssistants(): MockResponse
    suspend fun getAssistantDetails(assistantId: String): MockResponse
    suspend fun deleteAssistant(assistantId: String): MockResponse
}
