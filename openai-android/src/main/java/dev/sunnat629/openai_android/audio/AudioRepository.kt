package dev.sunnat629.openai_android.audio

data class MockResponse(val result: String)

interface AudioRepository {

    // Audio Operations
    suspend fun createAudioSpeech(): MockResponse
    suspend fun createAudioTranscription(): MockResponse
    suspend fun createAudioTranslation(): MockResponse
}