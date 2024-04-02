/**
 * @author  Mohi Us Sunnat
 * @date    01.04.24
 * Copyright ©2024 Sunnat629.dev. All rights reserved.
 */

package dev.sunnat629.openai_client.models.moderation

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
