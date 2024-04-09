/**
 * @author  Mohi Us Sunnat
 * @date    01.04.24
 * Copyright ©2024 Sunnat629.dev. All rights reserved.
 */

package dev.sunnat629.ai_client.apis.fineTuning

import dev.sunnat629.ai_client.models.fineTuning.CreateFineTuningRequest
import dev.sunnat629.ai_client.models.fineTuning.FineTuningResponse
import dev.sunnat629.ai_client.networks.getRequest
import dev.sunnat629.ai_client.networks.postRequest
import io.ktor.client.HttpClient

class FineTuningRepositoryImpl(private val httpClient: HttpClient) : FineTuningRepository {

    private val baseUrl = "https://api.openai.com/v1/fine-tunes"

    override suspend fun createFineTuningJob(request: CreateFineTuningRequest): FineTuningResponse {
        return httpClient.postRequest(
            url = baseUrl,
            request = request
        )
    }

    override suspend fun listFineTuningJobs(): List<FineTuningResponse> {
        return httpClient.getRequest(
            url = baseUrl
        )
    }

    override suspend fun retrieveFineTuningJob(fineTuneId: String): FineTuningResponse {
        return httpClient.getRequest(
            url = "$baseUrl/$fineTuneId"
        )
    }

    override suspend fun cancelFineTuningJob(fineTuneId: String): Unit {
        return httpClient.postRequest(
            url = "$baseUrl/$fineTuneId/cancel",
            request = ""
        )
    }
}