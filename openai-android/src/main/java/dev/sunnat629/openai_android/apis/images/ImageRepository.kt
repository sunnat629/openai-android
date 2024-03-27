package dev.sunnat629.openai_android.apis.images

import io.ktor.client.HttpClient
import io.ktor.client.request.post

data class MockResponse(val result: String)

interface ImageRepository {

    // Images Operations
    suspend fun createImageGeneration(): MockResponse
    suspend fun createImageEdit(): MockResponse
    suspend fun createImageVariation(): MockResponse
}

class ImageRepositoryImpl(private val client: HttpClient): ImageRepository {
    private val baseUrl = "https://api.openai.com/v1/images"

    override suspend fun createImageGeneration(): MockResponse =
        client.post("$baseUrl/generations") { /* Request body */ }

    override suspend fun createImageEdit(): MockResponse = client.post("$baseUrl/edits") { /* Request body */ }
    override suspend fun createImageVariation(): MockResponse =
        client.post("$baseUrl/variations") { /* Request body */ }
}