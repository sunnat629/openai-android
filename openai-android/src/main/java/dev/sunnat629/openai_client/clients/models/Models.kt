package dev.sunnat629.openai_client.clients.models

import dev.sunnat629.openai_client.apis.models.ModelsRepository
import dev.sunnat629.openai_client.clients.BaseUseCases
import dev.sunnat629.openai_client.models.models.ListModelsResponse
import dev.sunnat629.openai_client.models.models.ModelResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * An interface extending BaseUseCases for model-specific operations.
 * Allows for setting up model parameters and provides methods to retrieve models
 * or specific model details.
 */
interface Models : BaseUseCases<Models> {

    /**
     * Retrieves a list of models available.
     *
     * @return A Flow emitting the list of models response over time.
     */
    suspend fun getModels(): Flow<ListModelsResponse>

    /**
     * Retrieves details of a specific model based on previously set parameters.
     *
     * @return A Flow emitting the model's details response over time.
     */
    suspend fun retrieveModel(): Flow<ModelResponse>
}

internal class ModelsImpl(private val repository: ModelsRepository) : Models {

    private var _model: String? = null
    private var _role: String? = null
    private var _text: String? = null
    private var _imageUrl: String? = null

    override fun model(model: String): Models {
        this._model = model
        return this
    }

    override fun role(role: String): Models {
        this._role = role
        return this
    }

    override fun text(text: String): Models {
        this._text = text
        return this
    }

    override fun imageUrl(imageUrl: String): Models {
        this._imageUrl = imageUrl
        return this
    }

    override suspend fun getModels(): Flow<ListModelsResponse> {
        return flow { emit(repository.listModels()) }
    }

    override suspend fun retrieveModel(): Flow<ModelResponse> {
        return flow { emit(repository.retrieveModel(_model!!)) }
    }
}