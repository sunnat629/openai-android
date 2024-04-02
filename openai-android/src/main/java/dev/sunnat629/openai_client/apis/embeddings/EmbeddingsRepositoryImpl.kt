/**
 * @author  Mohi Us Sunnat
 * @date    01.04.24
 * Copyright ©2024 Sunnat629.dev. All rights reserved.
 */

package dev.sunnat629.openai_client.apis.embeddings

import dev.sunnat629.openai_client.models.embeddings.CreateEmbeddingsRequest
import dev.sunnat629.openai_client.models.embeddings.CreateEmbeddingsResponse
import dev.sunnat629.openai_client.networks.ApiResult
import dev.sunnat629.openai_client.networks.postRequest
import io.ktor.client.HttpClient

class EmbeddingsRepositoryImpl(private val client: HttpClient) : EmbeddingsRepository {
    private val baseUrl = "https://api.openai.com/v1/embeddings"

    override suspend fun createEmbedding(request: CreateEmbeddingsRequest): ApiResult<CreateEmbeddingsResponse> {
        return client.postRequest(baseUrl, request)
    }
}