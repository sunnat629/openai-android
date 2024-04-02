/**
 * @author  Mohi Us Sunnat
 * @date    01.04.24
 * Copyright ©2024 Sunnat629.dev. All rights reserved.
 */

package dev.sunnat629.openai_client.models.embeddings

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Request model for creating text embeddings.
 *
 * @property input The input text to embed, represented as a list of strings. Supports multiple inputs.
 * @property model The model identifier to use for generating embeddings.
 * @property encodingFormat Optionally specifies the format ("float" or "base64") for the embeddings.
 * @property dimensions Optionally specifies the number of dimensions for the output embeddings.
 * @property user Optionally specifies a unique identifier for the end-user.
 */
@Serializable
data class CreateEmbeddingsRequest(
    @SerialName("input") val input: List<String>,
    @SerialName("model") val model: String,
    @SerialName("encoding_format") val encodingFormat: String? = null,
    @SerialName("dimensions") val dimensions: Int? = null,
    @SerialName("user") val user: String? = null
)

/**
 * Response model for embedding creation.
 *
 * @property objectContent The type of the response object, always "list" for this API.
 * @property data The list of embedding data objects, each corresponding to an input string.
 * @property model The model used for generating the embeddings.
 * @property usage Statistics on the request usage, including token counts.
 */
@Serializable
data class CreateEmbeddingsResponse(
    @SerialName("object") val objectContent: String,
    @SerialName("data") val data: List<EmbeddingData>,
    @SerialName("model") val model: String,
    @SerialName("usage") val usage: UsageInfo,
)

/**
 * Embedding vector data for a single input.
 *
 * @property objectContent The type of the data object, "embedding".
 * @property embedding The numerical embedding vector.
 * @property index The index of this embedding in the response data list.
 */
@Serializable
data class EmbeddingData(
    @SerialName("object") val objectContent: String,
    @SerialName("embedding") val embedding: List<Float>,
    @SerialName("index") val index: Int,
)

/**
 * Usage information for the embedding request.
 *
 * @property promptTokens The count of tokens in the prompt.
 * @property totalTokens The total count of tokens processed.
 */
@Serializable
data class UsageInfo(
    @SerialName("prompt_tokens") val promptTokens: Int,
    @SerialName("total_tokens") val totalTokens: Int,
)
