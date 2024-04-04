/**
 * @author  Mohi Us Sunnat
 * @date    01.04.24
 * Copyright ©2024 Sunnat629.dev. All rights reserved.
 */

package dev.sunnat629.openai_client.clients

import dev.sunnat629.openai_client.apis.audio.AudioRepository
import dev.sunnat629.openai_client.apis.audio.AudioRepositoryImpl
import dev.sunnat629.openai_client.models.audio.CreateSpeechRequest
import dev.sunnat629.openai_client.models.audio.CreateSpeechResponse
import dev.sunnat629.openai_client.models.audio.CreateTranscriptionRequest
import dev.sunnat629.openai_client.models.audio.CreateTranscriptionResponse
import dev.sunnat629.openai_client.models.audio.CreateTranslationRequest
import dev.sunnat629.openai_client.models.audio.CreateTranslationResponse
import dev.sunnat629.openai_client.models.audio.ResponseFormat
import dev.sunnat629.openai_client.models.audio.TTSModel
import dev.sunnat629.openai_client.models.audio.TimestampGranularity
import dev.sunnat629.openai_client.models.audio.Voice
import dev.sunnat629.openai_client.networks.ApiResult

interface Audio {

    /**
     * Sets the model to be used for the audio operation.
     *
     * @param model One of the available TTS models. For example, tts-1 or tts-1-hd.
     * @return [Audio] instance for chaining.
     */
    fun model(model: TTSModel): Audio

    /**
     * Sets the input text for generating audio.
     *
     * @param input The text to generate audio for, with a maximum length of 4096 characters.
     * @return [Audio] instance for chaining.
     */
    fun input(input: String): Audio

    /**
     * Specifies the voice to use when generating the audio.
     *
     * @param voice The voice for the generated audio. Supported voices include alloy, echo, etc.
     * @return [Audio] instance for chaining.
     */
    fun voice(voice: Voice): Audio

    /**
     * Sets the file content for transcription or translation.
     *
     * @param file The audio file object to transcribe or translate.
     * @return [Audio] instance for chaining.
     */
    fun file(file: String): Audio

    /**
     * Sets the prompt to guide the model's style or to continue a previous segment.
     *
     * @param prompt The guiding text for the model.
     * @return [Audio] instance for chaining.
     */
    fun prompt(prompt: String): Audio

    /**
     * Specifies the format for the audio or transcript output.
     *
     * @param format The response format, such as mp3 or json.
     * @return [Audio] instance for chaining.
     */
    fun responseFormat(format: ResponseFormat): Audio

    /**
     * Sets the speed of the generated audio.
     *
     * @param speed The speed of the audio, ranging from 0.25 to 4.0.
     * @return [Audio] instance for chaining.
     */
    fun speed(speed: Double): Audio

    /**
     * Specifies the granularities for timestamps in the transcription.
     *
     * @param list A list of granularities, such as word or segment.
     * @return [Audio] instance for chaining.
     */
    fun timestampGranularities(list: List<TimestampGranularity>): Audio

    /**
     * Sets the temperature for generating responses.
     *
     * @param temperature The creativity temperature, ranging from 0 to 1.
     * @return [Audio] instance for chaining.
     */
    fun temperature(temperature: Double): Audio



    suspend fun speech(request: CreateSpeechRequest): CreateSpeechResponse
    suspend fun transcription(request: CreateTranscriptionRequest): CreateTranscriptionResponse
    suspend fun translation(request: CreateTranslationRequest): CreateTranslationResponse
}

class AudioImpl(private val repositoryImpl: AudioRepository): Audio {

    private var _model: String? = null
    private var _input: String? = null
    private var _voice: String? = null
    private var _file: String? = null
    private var _responseFormat: String? = null
    private var _timestampGranularities: String? = null
    private var _speed: Double = 1.0
    override fun model(model: TTSModel): Audio {
        TODO("Not yet implemented")
    }

    override fun input(input: String): Audio {
        TODO("Not yet implemented")
    }

    override fun voice(voice: Voice): Audio {
        TODO("Not yet implemented")
    }

    override fun file(file: String): Audio {
        TODO("Not yet implemented")
    }

    override fun prompt(prompt: String): Audio {
        TODO("Not yet implemented")
    }

    override fun responseFormat(format: ResponseFormat): Audio {
        TODO("Not yet implemented")
    }

    override fun speed(speed: Double): Audio {
        TODO("Not yet implemented")
    }

    override fun timestampGranularities(list: List<TimestampGranularity>): Audio {
        TODO("Not yet implemented")
    }

    override fun temperature(temperature: Double): Audio {
        TODO("Not yet implemented")
    }


    override suspend fun speech(request: CreateSpeechRequest): CreateSpeechResponse {
        TODO("Not yet implemented")
    }

    override suspend fun transcription(request: CreateTranscriptionRequest): CreateTranscriptionResponse {
        TODO("Not yet implemented")
    }

    override suspend fun translation(request: CreateTranslationRequest): CreateTranslationResponse {
        TODO("Not yet implemented")
    }

}