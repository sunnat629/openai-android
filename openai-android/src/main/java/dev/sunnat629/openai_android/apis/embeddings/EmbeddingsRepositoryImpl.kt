/**
 * @author  Mohi Us Sunnat
 * @date    01.04.24
 * Copyright ©2024 Sunnat629.dev. All rights reserved.
 */

package dev.sunnat629.openai_android.apis.embeddings

import dev.sunnat629.openai_android.models.embeddings.CreateEmbeddingsRequest
import dev.sunnat629.openai_android.models.embeddings.CreateEmbeddingsResponse
import dev.sunnat629.openai_android.networks.ApiResult
import dev.sunnat629.openai_android.networks.postRequest
import io.ktor.client.HttpClient

class EmbeddingsRepositoryImpl(private val client: HttpClient) : EmbeddingsRepository {
    private val baseUrl = "https://api.openai.com/v1/embeddings"

    override suspend fun createEmbedding(request: CreateEmbeddingsRequest): ApiResult<CreateEmbeddingsResponse> {
        return client.postRequest(baseUrl, request)
    }
}