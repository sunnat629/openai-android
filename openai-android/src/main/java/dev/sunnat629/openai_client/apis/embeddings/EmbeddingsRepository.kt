/**
 * @author  Mohi Us Sunnat
 * @date    01.04.24
 * Copyright ©2024 Sunnat629.dev. All rights reserved.
 */

package dev.sunnat629.openai_client.apis.embeddings

import dev.sunnat629.openai_client.models.embeddings.CreateEmbeddingsRequest
import dev.sunnat629.openai_client.models.embeddings.CreateEmbeddingsResponse
import dev.sunnat629.openai_client.networks.ApiResult

interface EmbeddingsRepository {

    // Embeddings Operations
    suspend fun createEmbedding(request: CreateEmbeddingsRequest): ApiResult<CreateEmbeddingsResponse>
}