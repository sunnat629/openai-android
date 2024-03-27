package dev.sunnat629.openai_android.apis.audio

import io.ktor.client.HttpClient
import io.ktor.client.request.post

data class MockResponse(val result: String)

interface AudioRepository {

    // Audio Operations
    suspend fun createAudioSpeech(): MockResponse
    suspend fun createAudioTranscription(): MockResponse
    suspend fun createAudioTranslation(): MockResponse
}

class AudioRepositoryImpl(private val client: HttpClient): AudioRepository {
    private val baseUrl = "https://api.openai.com/v1/audio"

    override suspend fun createAudioSpeech(): MockResponse = client.post("$baseUrl/speech") { /* Request body */ }
    override suspend fun createAudioTranscription(): MockResponse =
        client.post("$baseUrl/transcriptions") { /* Request body */ }

    override suspend fun createAudioTranslation(): MockResponse =
        client.post("$baseUrl/translations") { /* Request body */ }
}
