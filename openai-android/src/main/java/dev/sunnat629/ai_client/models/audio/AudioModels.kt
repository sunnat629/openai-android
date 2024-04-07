/**
 * @author  Mohi Us Sunnat
 * @date    01.04.24
 * Copyright ©2024 Sunnat629.dev. All rights reserved.
 */

package dev.sunnat629.ai_client.models.audio

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Request model for generating speech from text.
 *
 * @param model The ID of the model to use for text-to-speech.
 * @param input The text input to convert to speech.
 * @param voice The voice ID to use for the generated audio.
 * @param responseFormat The audio format for the response (e.g., "mp3", "wav").
 * @param speed The speed of the generated audio, ranging from 0.25 to 4.0.
 */
@Serializable
data class CreateSpeechRequest(
    @SerialName("model") val model: String? = null,
    @SerialName("input") val input: String? = null,
    @SerialName("voice") val voice: String? = null,
    @SerialName("response_format") val responseFormat: String? = null,
    @SerialName("speed") val speed: Double? = null,
)

/**
 * Response model for the Create Speech API.
 *
 * This example assumes the API returns a URL to the generated speech audio.
 * Actual implementation might return the audio file directly or in a different format.
 */
@Serializable
data class CreateSpeechResponse(
    @SerialName("audio_url") val audioUrl: String? = null,
)

/**
 * Request model for transcribing speech to text.
 *
 * @param model The model ID to use for the transcription.
 */
data class TranscriptionRequest(
    val model: String? = null,
    val responseFormat: String? = null,
    val timestampGranularities: List<TimestampGranularity>? = null,
)

/**
 * Response model for the Create Transcription API.
 *
 * @param text The transcribed text from the audio input.
 */
@Serializable
data class TranscriptionResponse(
    @SerialName("task")
    val task: String? = null,
    @SerialName("language")
    val language: String? = null,
    @SerialName("duration")
    val duration: Double? = null,
    @SerialName("text")
    val text: String? = null,
    @SerialName("words")
    val words: List<Word>? = null,
    val segments: List<Segment>? = null,
) {

    @Serializable
    data class Segment(
        @SerialName("id")
        val id: Int? = null,
        @SerialName("seek")
        val seek: Int? = null,
        @SerialName("start")
        val start: Double? = null,
        @SerialName("end")
        val end: Double? = null,
        @SerialName("text")
        val text: String? = null,
        @SerialName("tokens")
        val tokens: List<Int?>? = null,
        @SerialName("temperature")
        val temperature: Double? = null,
        @SerialName("avg_logprob")
        val avgLogprob: Double? = null,
        @SerialName("compression_ratio")
        val compressionRatio: Double? = null,
        @SerialName("no_speech_prob")
        val noSpeechProb: Double? = null
    )

    @Serializable
    data class Word(
        @SerialName("word")
        val word: String? = null,
        @SerialName("start")
        val start: Double? = null,
        @SerialName("end")
        val end: Double? = null
    )
}

/**
 * Request model for translating spoken language in an audio file.
 *
 * @param file The audio file containing spoken language to translate. Placeholder for binary data.
 * @param model The model ID to use for the translation.
 */
data class TranslationRequest(
     val model: String? = null,
     val prompt: String? = null,
    val responseFormat: String? = null,
     val temperature: Double? = null,
)

/**
 * Response model for the Create Translation API.
 *
 * @param text The translated text from the audio input.
 */
@Serializable
data class TranslationResponse(
    @SerialName("text") val text: String? = null,
)

enum class TTSModel(val value: String) {
    TTS1("tts-1"),
    TTS1HD("tts-1-hd")
}

enum class Voice(val value: String) {
    ALLOY("alloy"),
    ECHO("echo"),
    FABLE("fable"),
    ONYX("onyx"),
    NOVA("nova"),
    SHIMMER("shimmer")
}

enum class ResponseFormat(val value: String) {
    MP3("mp3"),
    OPUS("opus"),
    AAC("aac"),
    FLAC("flac"),
    WAV("wav"),
    PCM("pcm")
}

enum class ResponseFormatString(val value: String) {
    JSON("json"),
    TEXT("text"),
    SRT("srt"),
    VERBOSE_JSON("verbose_json"),
    VTT("vtt")
}

enum class TimestampGranularity(val value: String) {
    WORD("word"),
    SEGMENT("segment")
}