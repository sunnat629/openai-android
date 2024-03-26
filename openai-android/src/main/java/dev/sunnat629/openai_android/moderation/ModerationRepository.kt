package dev.sunnat629.openai_android.moderation

data class MockResponse(val result: String)

interface ModerationRepository {

    // Moderation Operations
    suspend fun createModeration(): MockResponse
}