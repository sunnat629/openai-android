package dev.sunnat629.openai_android.apis.moderation

import io.ktor.client.HttpClient
import io.ktor.client.request.post

data class MockResponse(val result: String)

interface ModerationRepository {

    // Moderation Operations
    suspend fun createModeration(): MockResponse
}

class ModerationRepositoryImpl(private val client: HttpClient): ModerationRepository {
    private val baseUrl = "https://api.openai.com/v1/moderations"

    override suspend fun createModeration(): MockResponse = client.post(baseUrl) { /* Request body */ }
}