package dev.sunnat629.openai_android.images

data class MockResponse(val result: String)

interface ImageRepository {

    // Images Operations
    suspend fun createImageGeneration(): MockResponse
    suspend fun createImageEdit(): MockResponse
    suspend fun createImageVariation(): MockResponse
}