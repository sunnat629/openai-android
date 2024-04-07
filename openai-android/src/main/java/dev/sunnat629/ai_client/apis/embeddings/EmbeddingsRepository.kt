/**
 * @author  Mohi Us Sunnat
 * @date    01.04.24
 * Copyright ©2024 Sunnat629.dev. All rights reserved.
 */

package dev.sunnat629.ai_client.apis.embeddings

import dev.sunnat629.ai_client.models.embeddings.EmbeddingsRequest
import dev.sunnat629.ai_client.models.embeddings.EmbeddingsResponse

interface EmbeddingsRepository {

    suspend fun createEmbedding(request: EmbeddingsRequest): EmbeddingsResponse
}