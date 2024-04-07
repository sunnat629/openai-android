/**
 * @author  Mohi Us Sunnat
 * @date    07.04.24
 * Copyright ©2024 Sunnat629.dev. All rights reserved.
 */

package dev.sunnat629.ai_client.clients

import dev.sunnat629.ai_client.apis.embeddings.EmbeddingsRepository
import dev.sunnat629.ai_client.models.embeddings.EmbeddingsRequest
import dev.sunnat629.ai_client.models.embeddings.EmbeddingsResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface Embeddings {

    /**
     * Sets a single input string for creating embeddings.
     *
     * @param input The input text to embed.
     * @return [Embeddings] instance for chaining.
     */
    fun input(input: String): Embeddings

    /**
     * Sets a list of input strings for creating embeddings.
     *
     * @param input The list of input texts to embed.
     * @return [Embeddings] instance for chaining.
     */
    fun input(input: List<String>): Embeddings

    /**
     * Sets the model identifier to use for generating embeddings.
     *
     * @param model The model identifier.
     * @return [Embeddings] instance for chaining.
     */
    fun model(model: String): Embeddings

    /**
     * Optionally specifies the format for the embeddings.
     *
     * @param encodingFormat The encoding format, e.g., "float" or "base64".
     * @return [Embeddings] instance for chaining.
     */
    fun encodingFormat(encodingFormat: String): Embeddings

    /**
     * Optionally specifies the number of dimensions for the output embeddings.
     *
     * @param dimensions The number of dimensions.
     * @return [Embeddings] instance for chaining.
     */
    fun dimensions(dimensions: Int): Embeddings

    /**
     * Optionally specifies a unique identifier for the end-user.
     *
     * @param user The user identifier.
     * @return [Embeddings] instance for chaining.
     */
    fun user(user: String): Embeddings

    /**
     * Creates the embeddings based on the provided [EmbeddingsRequest].
     *
     * @return [Flow]<[EmbeddingsResponse]> that emits the result of the embeddings creation.
     */
    fun create(): Flow<EmbeddingsResponse>
}

class EmbeddingsImpl(private val repository: EmbeddingsRepository): Embeddings {
    private var _input: List<String>? = null
    private var _model: String? = null
    private var _encodingFormat: String? = null
    private var _dimensions: Int? = null
    private var _user: String? = null

    override fun input(input: String): Embeddings {
        this._input = listOf(input)
        return this
    }

    override fun input(input: List<String>): Embeddings {
        this._input = input
        return this
    }

    override fun model(model: String): Embeddings {
        this._model = model
        return this
    }

    override fun encodingFormat(encodingFormat: String): Embeddings {
        this._encodingFormat = encodingFormat
        return this
    }

    override fun dimensions(dimensions: Int): Embeddings {
        this._dimensions = dimensions
        return this
    }

    override fun user(user: String): Embeddings {
        this._user = user
        return this
    }

    override fun create(): Flow<EmbeddingsResponse> = flow {
        val request = EmbeddingsRequest(
            model = _model?: throw IllegalArgumentException("Model is required"),
            input = _input?: throw IllegalArgumentException("Input is required"),
            encodingFormat = _encodingFormat,
            dimensions = _dimensions,
            user = _user
        )
        emit(repository.createEmbedding(request))
    }
}