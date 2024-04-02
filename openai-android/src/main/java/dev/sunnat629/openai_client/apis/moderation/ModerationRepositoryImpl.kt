/**
 * @author  Mohi Us Sunnat
 * @date    01.04.24
 * Copyright ©2024 Sunnat629.dev. All rights reserved.
 */

package dev.sunnat629.openai_client.apis.moderation

import dev.sunnat629.openai_client.networks.ApiResult
import dev.sunnat629.openai_client.networks.postRequest
import io.ktor.client.HttpClient

class ModerationsRepositoryImpl(private val httpClient: HttpClient) : ModerationsRepository {

    private val baseUrl = "https://api.openai.com/v1/moderations"

    override suspend fun createModeration(request: Any): ApiResult<Any> {
        return httpClient.postRequest(
            url = baseUrl,
            request = request
        )
    }
}