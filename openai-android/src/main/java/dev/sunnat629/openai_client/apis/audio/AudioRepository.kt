/**
 * @author  Mohi Us Sunnat
 * @date    01.04.24
 * Copyright ©2024 Sunnat629.dev. All rights reserved.
 */

package dev.sunnat629.openai_client.apis.audio

import dev.sunnat629.openai_client.models.audio.CreateSpeechRequest
import dev.sunnat629.openai_client.models.audio.TranscriptionResponse
import dev.sunnat629.openai_client.models.audio.CreateTranslationRequest
import dev.sunnat629.openai_client.models.audio.TranslationResponse
import kotlinx.coroutines.flow.Flow

interface AudioRepository {

    fun createSpeech(request: CreateSpeechRequest): Flow<ByteArray>
    suspend fun createTranscription(model: String, byteArray: ByteArray): TranscriptionResponse
    suspend fun createTranslation(request: CreateTranslationRequest): TranslationResponse
}