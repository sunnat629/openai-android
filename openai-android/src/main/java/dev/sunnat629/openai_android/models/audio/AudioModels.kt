/**
 * @author  Mohi Us Sunnat
 * @date    01.04.24
 * Copyright ©2024 Sunnat629.dev. All rights reserved.
 */

package dev.sunnat629.openai_android.models.audio

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
    @SerialName("model") val model: String,
    @SerialName("input") val input: String,
    @SerialName("voice") val voice: String,
    @SerialName("response_format") val responseFormat: String? = null,
    @SerialName("speed") val speed: Double? = null
)

/**
 * Response model for the Create Speech API.
 *
 * This example assumes the API returns a URL to the generated speech audio.
 * Actual implementation might return the audio file directly or in a different format.
 */
@Serializable
data class CreateSpeechResponse(
    @SerialName("audio_url") val audioUrl: String
)

/**
 * Request model for transcribing speech to text.
 *
 * @param file The audio file to be transcribed. In an actual request, this would be sent as binary data.
 * @param model The model ID to use for the transcription.
 * @param language The language of the input audio.
 */
@Serializable
data class CreateTranscriptionRequest(
    @SerialName("file") val file: String, // Placeholder for binary data
    @SerialName("model") val model: String,
    @SerialName("language") val language: String? = null
)

/**
 * Response model for the Create Transcription API.
 *
 * @param text The transcribed text from the audio input.
 */
@Serializable
data class CreateTranscriptionResponse(
    @SerialName("text") val text: String
)

/**
 * Request model for translating spoken language in an audio file.
 *
 * @param file The audio file containing spoken language to translate. Placeholder for binary data.
 * @param model The model ID to use for the translation.
 */
@Serializable
data class CreateTranslationRequest(
    @SerialName("file") val file: String, // Placeholder for binary data
    @SerialName("model") val model: String
)

/**
 * Response model for the Create Translation API.
 *
 * @param text The translated text from the audio input.
 */
@Serializable
data class CreateTranslationResponse(
    @SerialName("text") val text: String
)