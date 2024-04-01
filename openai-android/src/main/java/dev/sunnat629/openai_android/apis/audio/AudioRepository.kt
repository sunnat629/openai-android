package dev.sunnat629.openai_android.apis.audio

import dev.sunnat629.openai_android.models.audio.CreateSpeechRequest
import dev.sunnat629.openai_android.models.audio.CreateSpeechResponse
import dev.sunnat629.openai_android.models.audio.CreateTranscriptionRequest
import dev.sunnat629.openai_android.models.audio.CreateTranscriptionResponse
import dev.sunnat629.openai_android.models.audio.CreateTranslationRequest
import dev.sunnat629.openai_android.models.audio.CreateTranslationResponse
import dev.sunnat629.openai_android.networks.ApiResult
import dev.sunnat629.openai_android.networks.postRequest
import io.ktor.client.HttpClient

interface AudioRepository {

    suspend fun createSpeech(request: CreateSpeechRequest): ApiResult<CreateSpeechResponse>
    suspend fun createTranscription(request: CreateTranscriptionRequest): ApiResult<CreateTranscriptionResponse>
    suspend fun createTranslation(request: CreateTranslationRequest): ApiResult<CreateTranslationResponse>
}

class AudioRepositoryImpl(private val client: HttpClient) : AudioRepository {

    override suspend fun createSpeech(request: CreateSpeechRequest): ApiResult<CreateSpeechResponse> {
        return client.postRequest(
            url = "https://api.openai.com/v1/audio/speech",
            request = request
        )
    }

    override suspend fun createTranscription(request: CreateTranscriptionRequest): ApiResult<CreateTranscriptionResponse> {
        return client.postRequest(
            url = "https://api.openai.com/v1/audio/transcriptions",
            request = request
        )
    }

    override suspend fun createTranslation(request: CreateTranslationRequest): ApiResult<CreateTranslationResponse> {
        return client.postRequest(
            url = "https://api.openai.com/v1/audio/translations",
            request = request
        )
    }
}
