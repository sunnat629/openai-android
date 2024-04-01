/**
 * @author  Mohi Us Sunnat
 * @date    01.04.24
 * Copyright ©2024 Sunnat629.dev. All rights reserved.
 */

package dev.sunnat629.openai_android

import dev.sunnat629.openai_android.apis.models.ModelsRepository
import dev.sunnat629.openai_android.models.models.ListModelsResponse
import dev.sunnat629.openai_android.models.models.ModelResponse
import dev.sunnat629.openai_android.models.openaAI.OpenAIBuilderConfig
import dev.sunnat629.openai_android.networks.ApiResult
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface OpenAI {
    suspend fun modelList(): ApiResult<ListModelsResponse>
    suspend fun retrieveModel(id: String): ApiResult<ModelResponse>


}

internal class OpenAIImpl(configModel: OpenAIBuilderConfig) : OpenAI, KoinComponent {
    private val modelsRepository: ModelsRepository by inject()

    init {
        openAiAndroidLibModuleKoin(configModel)
    }

    override suspend fun modelList(): ApiResult<ListModelsResponse> {
        return modelsRepository.listModels()
    }

    override suspend fun retrieveModel(id: String): ApiResult<ModelResponse> {
        return modelsRepository.retrieveModel(id)
    }
}