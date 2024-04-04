/**
 * @author  Mohi Us Sunnat
 * @date    01.04.24
 * Copyright ©2024 Sunnat629.dev. All rights reserved.
 */

package dev.sunnat629.openai_client.models.moderation

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ModerationRequest(
    @SerialName("input") val input: String
)

@Serializable
data class ModerationResponse(
    @SerialName("id")
    val id: String? = null,
    @SerialName("model")
    val model: String? = null,
    @SerialName("results")
    val results: List<Result?>? = null
) {
    @Serializable
    data class Result(
        @SerialName("flagged")
        val flagged: Boolean? = null,
        @SerialName("categories")
        val categories: Categories? = null,
        @SerialName("category_scores")
        val categoryScores: CategoryScores? = null
    ) {
        @Serializable
        data class Categories(
            @SerialName("sexual")
            val sexual: Boolean? = null,
            @SerialName("hate")
            val hate: Boolean? = null,
            @SerialName("harassment")
            val harassment: Boolean? = null,
            @SerialName("self-harm")
            val selfHarm: Boolean? = null,
            @SerialName("sexual/minors")
            val sexualminors: Boolean? = null,
            @SerialName("hate/threatening")
            val hatethreatening: Boolean? = null,
            @SerialName("violence/graphic")
            val violencegraphic: Boolean? = null,
            @SerialName("self-harm/intent")
            val selfHarmintent: Boolean? = null,
            @SerialName("self-harm/instructions")
            val selfHarminstructions: Boolean? = null,
            @SerialName("harassment/threatening")
            val harassmentthreatening: Boolean? = null,
            @SerialName("violence")
            val violence: Boolean? = null
        )

        @Serializable
        data class CategoryScores(
            @SerialName("sexual")
            val sexual: Double? = null,
            @SerialName("hate")
            val hate: Double? = null,
            @SerialName("harassment")
            val harassment: Double? = null,
            @SerialName("self-harm")
            val selfHarm: Double? = null,
            @SerialName("sexual/minors")
            val sexualminors: Double? = null,
            @SerialName("hate/threatening")
            val hatethreatening: Double? = null,
            @SerialName("violence/graphic")
            val violencegraphic: Double? = null,
            @SerialName("self-harm/intent")
            val selfHarmintent: Double? = null,
            @SerialName("self-harm/instructions")
            val selfHarminstructions: Double? = null,
            @SerialName("harassment/threatening")
            val harassmentthreatening: Double? = null,
            @SerialName("violence")
            val violence: Double? = null
        )
    }
}