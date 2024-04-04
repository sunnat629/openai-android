/**
 * @author  Mohi Us Sunnat
 * @date    01.04.24
 * Copyright ©2024 Sunnat629.dev. All rights reserved.
 */

package dev.sunnat629.openai_client.apis.audio

import dev.sunnat629.openai_client.models.audio.CreateSpeechRequest
import dev.sunnat629.openai_client.models.audio.CreateTranscriptionRequest
import dev.sunnat629.openai_client.models.audio.CreateTranscriptionResponse
import dev.sunnat629.openai_client.models.audio.CreateTranslationRequest
import dev.sunnat629.openai_client.models.audio.CreateTranslationResponse
import kotlinx.coroutines.flow.Flow

interface AudioRepository {

    fun createSpeech(request: CreateSpeechRequest): Flow<ByteArray>
    suspend fun createTranscription(request: CreateTranscriptionRequest): CreateTranscriptionResponse
    suspend fun createTranslation(request: CreateTranslationRequest): CreateTranslationResponse
}