/**
 * @author  Mohi Us Sunnat
 * @date    01.04.24
 * Copyright ©2024 Sunnat629.dev. All rights reserved.
 */

package dev.sunnat629.openai_android.models.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ModelResponse(
    @SerialName("id") val id: String,
    @SerialName("object") val objectContent: String,
    @SerialName("created") val created: Long,
    @SerialName("owned_by") val ownedBy: String
)

@Serializable
data class ListModelsResponse(
    @SerialName("data") val data: List<ModelResponse>,
    @SerialName("object") val objectContent: String,
)

enum class OpenAIModel(val id: String) {
    GPT_4_0125_PREVIEW("gpt-4-0125-preview"),
    GPT_4_TURBO_PREVIEW("gpt-4-turbo-preview"),
    GPT_4_1106_PREVIEW("gpt-4-1106-preview"),
    GPT_4_VISION_PREVIEW("gpt-4-vision-preview"),
    GPT_4_1106_VISION_PREVIEW("gpt-4-1106-vision-preview"),
    GPT_4("gpt-4"),
    GPT_4_0613("gpt-4-0613"),
    GPT_4_32K("gpt-4-32k"),
    GPT_4_32K_0613("gpt-4-32k-0613"),
    GPT_3_5_TURBO_0125("gpt-3.5-turbo-0125"),
    GPT_3_5_TURBO("gpt-3.5-turbo"),
    GPT_3_5_TURBO_1106("gpt-3.5-turbo-1106"),
    GPT_3_5_TURBO_INSTRUCT("gpt-3.5-turbo-instruct"),
    GPT_3_5_TURBO_16K("gpt-3.5-turbo-16k"),
    GPT_3_5_TURBO_0613("gpt-3.5-turbo-0613"),
    GPT_3_5_TURBO_16K_0613("gpt-3.5-turbo-16k-0613"),
    DALL_E_3("dall-e-3"),
    DALL_E_2("dall-e-2"),
    TTS_1("tts-1"),
    TTS_1_HD("tts-1-hd"),
    WHISPER_1("whisper-1"),
    TEXT_EMBEDDING_3_LARGE("text-embedding-3-large"),
    TEXT_EMBEDDING_3_SMALL("text-embedding-3-small"),
    TEXT_EMBEDDING_ADA_002("text-embedding-ada-002"),
    TEXT_MODERATION_LATEST("text-moderation-latest"),
    TEXT_MODERATION_STABLE("text-moderation-stable"),
    TEXT_MODERATION_007("text-moderation-007"),
    BABBAGE_002("babbage-002"),
    DAVINCI_002("davinci-002");

    companion object {
        fun fromId(id: String): OpenAIModel? = entries.find { it.id == id }
    }
}