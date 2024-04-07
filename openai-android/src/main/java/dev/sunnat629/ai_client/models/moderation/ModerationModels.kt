/**
 * @author  Mohi Us Sunnat
 * @date    01.04.24
 * Copyright ©2024 Sunnat629.dev. All rights reserved.
 */

package dev.sunnat629.ai_client.models.moderation

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(with = ModerationRequestSerializer::class)
data class ModerationRequest(
    val input: Input
)

sealed class Input {
    @Serializable
    data class Single(val value: String) : Input()

    @Serializable
    data class Multiple(val values: List<String>) : Input()
}

object ModerationRequestSerializer : KSerializer<ModerationRequest> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("ModerationRequest") {
        element<String>("input")
    }

    override fun serialize(encoder: Encoder, value: ModerationRequest) {
        val elemDescriptor = encoder.beginStructure(descriptor)
        when (val input = value.input) {
            is Input.Single -> elemDescriptor.encodeStringElement(descriptor, 0, input.value)
            is Input.Multiple -> elemDescriptor.encodeSerializableElement(descriptor, 0, ListSerializer(String.serializer()), input.values)
        }
        elemDescriptor.endStructure(descriptor)
    }

    override fun deserialize(decoder: Decoder): ModerationRequest {
        val dec = decoder.beginStructure(descriptor)
        val index = dec.decodeElementIndex(descriptor)
        lateinit var input: Input
        if (index == 0) {
            try {
                input = Input.Single(dec.decodeStringElement(descriptor, 0))
            } catch (e: SerializationException) {
                input = Input.Multiple(dec.decodeSerializableElement(descriptor, 0, ListSerializer(String.serializer())))
            }
        }
        dec.endStructure(descriptor)
        return ModerationRequest(input)
    }
}


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