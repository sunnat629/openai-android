/**
 * @author  Mohi Us Sunnat
 * @date    01.04.24
 * Copyright ©2024 Sunnat629.dev. All rights reserved.
 */

package dev.sunnat629.openai_client.apis.threads

import dev.sunnat629.openai_client.models.threads.CreateThreadRequest
import dev.sunnat629.openai_client.models.threads.ThreadResponse
import dev.sunnat629.openai_client.networks.ApiResult
import dev.sunnat629.openai_client.networks.getRequest
import dev.sunnat629.openai_client.networks.postRequest
import io.ktor.client.HttpClient

class ThreadRepositoryImpl(private val httpClient: HttpClient) : ThreadRepository {

    private val baseUrl = "https://api.openai.com/v1/threads"

    override suspend fun createThread(request: CreateThreadRequest): ApiResult<ThreadResponse> {
        return httpClient.postRequest(
            url = baseUrl,
            request = request
        )
    }

    override suspend fun listThreads(): ApiResult<List<ThreadResponse>> {
        return httpClient.getRequest(
            url = baseUrl
        )
    }

    override suspend fun retrieveThread(threadId: String): ApiResult<ThreadResponse> {
        return httpClient.getRequest(
            url = "$baseUrl/$threadId"
        )
    }
}