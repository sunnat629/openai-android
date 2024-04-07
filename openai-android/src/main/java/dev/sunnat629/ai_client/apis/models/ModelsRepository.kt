/**
 * @author  Mohi Us Sunnat
 * @date    01.04.24
 * Copyright ©2024 Sunnat629.dev. All rights reserved.
 */

package dev.sunnat629.ai_client.apis.models

import dev.sunnat629.ai_client.models.models.ListModelsResponse
import dev.sunnat629.ai_client.models.models.ModelResponse

interface ModelsRepository {

    suspend fun listModels(): ListModelsResponse
    suspend fun retrieveModel(modelId: String): ModelResponse
}