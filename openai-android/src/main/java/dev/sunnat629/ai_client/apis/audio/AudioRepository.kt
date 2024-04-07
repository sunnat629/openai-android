/**
 * @author  Mohi Us Sunnat
 * @date    01.04.24
 * Copyright ©2024 Sunnat629.dev. All rights reserved.
 */

package dev.sunnat629.ai_client.apis.audio

import dev.sunnat629.ai_client.models.audio.CreateSpeechRequest
import dev.sunnat629.ai_client.models.audio.TranscriptionResponse
import dev.sunnat629.ai_client.models.audio.TranslationRequest
import dev.sunnat629.ai_client.models.audio.TranscriptionRequest
import dev.sunnat629.ai_client.models.audio.TranslationResponse
import kotlinx.coroutines.flow.Flow

interface AudioRepository {

    fun createSpeech(request: CreateSpeechRequest): Flow<ByteArray>
    suspend fun createTranscription(request: TranscriptionRequest, byteArray: ByteArray): TranscriptionResponse
    suspend fun createTranslation(request: TranslationRequest, byteArray: ByteArray): TranslationResponse
}