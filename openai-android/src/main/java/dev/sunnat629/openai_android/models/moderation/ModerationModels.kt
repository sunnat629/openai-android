package dev.sunnat629.openai_android.models.moderation

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateModerationRequest(
    @SerialName("input") val input: String
)

@Serializable
data class ModerationResponse(
    @SerialName("id") val id: String,
    @SerialName("object") val objectContent: String,
    @SerialName("results") val results: List<ModerationResult>
)

@Serializable
data class ModerationResult(
    @SerialName("category") val category: String,
    @SerialName("score") val score: Double
)
