/**
 * @author  Mohi Us Sunnat
 * @date    01.04.24
 * Copyright ©2024 Sunnat629.dev. All rights reserved.
 */

package dev.sunnat629.openai_client.apis.audio

import dev.sunnat629.openai_client.models.audio.CreateSpeechRequest
import dev.sunnat629.openai_client.models.audio.CreateSpeechResponse
import dev.sunnat629.openai_client.models.audio.CreateTranscriptionRequest
import dev.sunnat629.openai_client.models.audio.CreateTranscriptionResponse
import dev.sunnat629.openai_client.models.audio.CreateTranslationRequest
import dev.sunnat629.openai_client.models.audio.CreateTranslationResponse
import dev.sunnat629.openai_client.networks.ApiResult
import dev.sunnat629.openai_client.networks.postRequest
import io.ktor.client.HttpClient

class AudioRepositoryImpl(private val client: HttpClient) : AudioRepository {

    private val baseUrl = "https://api.openai.com/v1/audio"
    override suspend fun createSpeech(request: CreateSpeechRequest): CreateSpeechResponse {
        return client.postRequest(
            url = "$baseUrl/speech",
            request = request
        )
    }

    override suspend fun createTranscription(request: CreateTranscriptionRequest): CreateTranscriptionResponse {
        return client.postRequest(
            url = "$baseUrl/transcriptions",
            request = request
        )
    }

    override suspend fun createTranslation(request: CreateTranslationRequest): CreateTranslationResponse {
        return client.postRequest(
            url = "$baseUrl/translations",
            request = request
        )
    }
}