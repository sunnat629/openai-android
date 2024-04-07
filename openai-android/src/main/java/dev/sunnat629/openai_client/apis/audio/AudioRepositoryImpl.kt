/**
 * @author  Mohi Us Sunnat
 * @date    01.04.24
 * Copyright ©2024 Sunnat629.dev. All rights reserved.
 */

package dev.sunnat629.openai_client.apis.audio

import android.util.Log
import dev.sunnat629.openai_client.models.audio.CreateSpeechRequest
import dev.sunnat629.openai_client.models.audio.TranscriptionResponse
import dev.sunnat629.openai_client.models.audio.TranslationRequest
import dev.sunnat629.openai_client.models.audio.TranscriptionRequest
import dev.sunnat629.openai_client.models.audio.TranslationResponse
import dev.sunnat629.openai_client.networks.postRequest
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.forms.formData
import io.ktor.client.request.forms.submitFormWithBinaryData
import io.ktor.client.utils.EmptyContent.contentType
import io.ktor.http.ContentType
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import io.ktor.util.InternalAPI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AudioRepositoryImpl(private val client: HttpClient) : AudioRepository {

    private val baseUrl = "https://api.openai.com/v1/audio"

    override fun createSpeech(request: CreateSpeechRequest): Flow<ByteArray> {
        return flow {
            emit(client.postRequest(
                url = "$baseUrl/speech",
                request = request
            ))
        }
    }

    @OptIn(InternalAPI::class)
    override suspend fun createTranscription(request: TranscriptionRequest, byteArray: ByteArray): TranscriptionResponse {
        val response = client.submitFormWithBinaryData(
            url = "$baseUrl/transcriptions",
            formData = formData {
                append("model", request.model ?: throw IllegalArgumentException("Model is required"))
                append("file", byteArray, Headers.build {
                    append(HttpHeaders.ContentDisposition, "filename=audio.mp3")
                })
                request.responseFormat?.let { append("response_format", it) }
                request.timestampGranularities?.let { append("timestamp_granularities", it) }
            }
        )
        return response.body()
    }

    override suspend fun createTranslation(request: TranslationRequest, byteArray: ByteArray): TranslationResponse {
        val response = client.submitFormWithBinaryData(
            url = "$baseUrl/translations",
            formData = formData {
                append("model", request.model ?: throw IllegalArgumentException("Model is required"))
                append("file", byteArray, Headers.build {
                    append(HttpHeaders.ContentDisposition, "filename=audio.mp3")
                })
                request.responseFormat?.let { append("response_format", it) }
                request.prompt?.let { append("prompt", it) }
                request.temperature?.let { append("temperature", it) }
            }
        )

        Log.w("ASDF",response.toString())
        return response.body()
    }
}