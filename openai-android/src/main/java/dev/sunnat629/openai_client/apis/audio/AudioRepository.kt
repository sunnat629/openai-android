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

interface AudioRepository {

    suspend fun createSpeech(request: CreateSpeechRequest): ApiResult<CreateSpeechResponse>
    suspend fun createTranscription(request: CreateTranscriptionRequest): ApiResult<CreateTranscriptionResponse>
    suspend fun createTranslation(request: CreateTranslationRequest): ApiResult<CreateTranslationResponse>
}