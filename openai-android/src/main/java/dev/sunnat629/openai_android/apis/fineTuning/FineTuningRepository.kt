package dev.sunnat629.openai_android.apis.fineTuning

import dev.sunnat629.openai_android.models.fineTuning.CreateFineTuningRequest
import dev.sunnat629.openai_android.models.fineTuning.FineTuningResponse
import dev.sunnat629.openai_android.networks.ApiResult
import dev.sunnat629.openai_android.networks.getRequest
import dev.sunnat629.openai_android.networks.postRequest
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.post

// FineTuningRepository.kt
interface FineTuningRepository {
    /** Creates a fine-tuning job */
    suspend fun createFineTuningJob(request: CreateFineTuningRequest): ApiResult<FineTuningResponse>
    /** Lists all fine-tuning jobs */
    suspend fun listFineTuningJobs(): ApiResult<List<FineTuningResponse>>
    /** Retrieves a fine-tuning job by ID */
    suspend fun retrieveFineTuningJob(fineTuneId: String): ApiResult<FineTuningResponse>
    /** Cancels a fine-tuning job */
    suspend fun cancelFineTuningJob(fineTuneId: String): ApiResult<Unit>
}

class FineTuningRepositoryImpl(private val httpClient: HttpClient) : FineTuningRepository {
    override suspend fun createFineTuningJob(request: CreateFineTuningRequest): ApiResult<FineTuningResponse> {
        return httpClient.postRequest(
            url = "https://api.openai.com/v1/fine-tunes",
            request = request
        )
    }

    override suspend fun listFineTuningJobs(): ApiResult<List<FineTuningResponse>> {
        return httpClient.getRequest(
            url = "https://api.openai.com/v1/fine-tunes"
        )
    }

    override suspend fun retrieveFineTuningJob(fineTuneId: String): ApiResult<FineTuningResponse> {
        return httpClient.getRequest(
            url = "https://api.openai.com/v1/fine-tunes/$fineTuneId"
        )
    }

    override suspend fun cancelFineTuningJob(fineTuneId: String): ApiResult<Unit> {
        return httpClient.postRequest(
            url = "https://api.openai.com/v1/fine-tunes/$fineTuneId/cancel",
            request = ""
        )
    }
}
