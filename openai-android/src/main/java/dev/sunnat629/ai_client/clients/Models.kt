package dev.sunnat629.ai_client.clients

import dev.sunnat629.ai_client.apis.models.ModelsRepository
import dev.sunnat629.ai_client.models.models.ListModelsResponse
import dev.sunnat629.ai_client.models.models.ModelResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * An interface extending BaseUseCases for model-specific operations.
 * Allows for setting up model parameters and provides methods to retrieve models
 * or specific model details.
 */
interface Models {

    /**
     * Sets the model identifier or name.
     *
     * @param model A string representing the model identifier.
     * @return The instance of the implementing class for fluent chaining.
     */
    fun model(model: String): Models

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

    override fun model(model: String): Models {
        this._model = model
        return this
    }

    override suspend fun getModels(): Flow<ListModelsResponse> {
        return flow { emit(repository.listModels()) }
    }

    override suspend fun retrieveModel(): Flow<ModelResponse> {
        return flow {
            if (_model == null) throw NullPointerException("Model is null")
            emit(repository.retrieveModel(_model!!))
        }
    }
}