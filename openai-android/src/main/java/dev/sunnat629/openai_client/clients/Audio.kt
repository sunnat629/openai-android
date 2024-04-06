/**
 * @author  Mohi Us Sunnat
 * @date    01.04.24
 * Copyright ©2024 Sunnat629.dev. All rights reserved.
 */

package dev.sunnat629.openai_client.clients

import android.content.Context
import android.net.Uri
import dev.sunnat629.openai_client.apis.audio.AudioRepository
import dev.sunnat629.openai_client.models.audio.CreateSpeechRequest
import dev.sunnat629.openai_client.models.audio.TranscriptionResponse
import dev.sunnat629.openai_client.models.audio.TranslationResponse
import dev.sunnat629.openai_client.models.audio.ResponseFormat
import dev.sunnat629.openai_client.models.audio.TTSModel
import dev.sunnat629.openai_client.models.audio.TimestampGranularity
import dev.sunnat629.openai_client.models.audio.Voice
import dev.sunnat629.openai_client.utils.uriToByteArray
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import java.io.File

interface Audio {

    /**
     * Sets the model to be used for the audio operation.
     *
     * @param model One of the available TTS models. For example, tts-1 or tts-1-hd.
     * @return [Audio] instance for chaining.
     */
    fun model(model: TTSModel): Audio

    fun model(model: String): Audio

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
    fun file(context: Context, file: Uri): Audio

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


    suspend fun speech(): Flow<ByteArray>
    suspend fun speechWithUri(context: Context): Flow<Uri>
    suspend fun transcription(): Flow<TranscriptionResponse>
    suspend fun translation(): Flow<TranslationResponse>
}

class AudioImpl(private val repository: AudioRepository) : Audio {

    private var _context: Context? = null
    private var _model: String? = null
    private var _input: String? = null
    private var _voice: Voice? = null
    private var _file: Uri? = null
    private var _prompt: String? = null
    private var _responseFormat: ResponseFormat? = null
    private var _speed: Double? = null
    private var _timestampGranularities: List<TimestampGranularity>? = null
    private var _temperature: Double? = null

    override fun model(model: TTSModel): Audio {
        this._model = model.value
        return this
    }

    override fun model(model: String): Audio {
        this._model = model
        return this
    }

    override fun input(input: String): Audio {
        this._input = input
        return this
    }

    override fun voice(voice: Voice): Audio {
        this._voice = voice
        return this
    }

    override fun file(context: Context, file: Uri): Audio {
        this._context = context
        this._file = file
        return this
    }

    override fun prompt(prompt: String): Audio {
        this._prompt = prompt
        return this
    }

    override fun responseFormat(format: ResponseFormat): Audio {
        this._responseFormat = format
        return this
    }

    override fun speed(speed: Double): Audio {
        this._speed = speed
        return this
    }

    override fun timestampGranularities(list: List<TimestampGranularity>): Audio {
        this._timestampGranularities = list
        return this
    }

    override fun temperature(temperature: Double): Audio {
        this._temperature = temperature
        return this
    }

    override suspend fun speech(): Flow<ByteArray> {
        val request = CreateSpeechRequest(
            model = _model,
            input =_input,
            voice =_voice?.value,
            responseFormat = _responseFormat?.value,
            speed = _speed,
        )
        return repository.createSpeech(request)
    }

    override suspend fun speechWithUri(context: Context): Flow<Uri> {
        return flow {
            val audioByteArray = speech().first()
            val outputFile = File(context.getExternalFilesDir(null), "speech.mp3")
            withContext(Dispatchers.IO) {
                outputFile.writeBytes(audioByteArray)
            }
            val fileUri: Uri = Uri.fromFile(outputFile)
            emit(fileUri)
        }
    }

    override suspend fun transcription(): Flow<TranscriptionResponse> {
        return flow {
            if (_model == null || _file == null) throw NullPointerException("Content is null")
            val byteArray = uriToByteArray(_context, _file!!) ?: throw NullPointerException("Content is null")
            emit(repository.createTranscription(_model!!, byteArray))
        }
    }

    override suspend fun translation(): Flow<TranslationResponse> {
        TODO("Not yet implemented")
    }

}