/**
 * @author  Mohi Us Sunnat
 * @date    01.04.24
 * Copyright ©2024 Sunnat629.dev. All rights reserved.
 */

package dev.sunnat629.openai_android

import dev.sunnat629.openai_android.apis.chats.ChatRepository
import dev.sunnat629.openai_android.apis.models.ModelsRepository
import dev.sunnat629.openai_android.models.chats.ChatRequest
import dev.sunnat629.openai_android.models.chats.ChatResponse
import dev.sunnat629.openai_android.models.models.ListModelsResponse
import dev.sunnat629.openai_android.models.models.ModelResponse
import dev.sunnat629.openai_android.models.openaAI.OpenAIBuilderConfig
import dev.sunnat629.openai_android.networks.ApiResult
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface OpenAI {

    suspend fun modelList(): ApiResult<ListModelsResponse>
    suspend fun retrieveModel(id: String): ApiResult<ModelResponse>


    suspend fun chat(request: ChatRequest): ApiResult<ChatResponse>
}

internal class OpenAIImpl(configModel: OpenAIBuilderConfig) : OpenAI, KoinComponent {
    private val modelsRepository: ModelsRepository by inject()
    private val chatRepository: ChatRepository by inject()

    init {
        openAiAndroidLibModuleKoin(configModel)
    }

    override suspend fun modelList(): ApiResult<ListModelsResponse> {
        return modelsRepository.listModels()
    }

    override suspend fun retrieveModel(id: String): ApiResult<ModelResponse> {
        return modelsRepository.retrieveModel(id)
    }

    override suspend fun chat(request: ChatRequest): ApiResult<ChatResponse> {
        return chatRepository.chat(request)
    }
}