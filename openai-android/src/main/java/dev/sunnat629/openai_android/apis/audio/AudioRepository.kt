/**
 * @author  Mohi Us Sunnat
 * @date    01.04.24
 * Copyright ©2024 Sunnat629.dev. All rights reserved.
 */

package dev.sunnat629.openai_android.apis.audio

import dev.sunnat629.openai_android.models.audio.CreateSpeechRequest
import dev.sunnat629.openai_android.models.audio.CreateSpeechResponse
import dev.sunnat629.openai_android.models.audio.CreateTranscriptionRequest
import dev.sunnat629.openai_android.models.audio.CreateTranscriptionResponse
import dev.sunnat629.openai_android.models.audio.CreateTranslationRequest
import dev.sunnat629.openai_android.models.audio.CreateTranslationResponse
import dev.sunnat629.openai_android.networks.ApiResult

interface AudioRepository {

    suspend fun createSpeech(request: CreateSpeechRequest): ApiResult<CreateSpeechResponse>
    suspend fun createTranscription(request: CreateTranscriptionRequest): ApiResult<CreateTranscriptionResponse>
    suspend fun createTranslation(request: CreateTranslationRequest): ApiResult<CreateTranslationResponse>
}