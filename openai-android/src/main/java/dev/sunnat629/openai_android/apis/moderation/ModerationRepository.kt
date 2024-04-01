package dev.sunnat629.openai_android.apis.moderation

import dev.sunnat629.openai_android.networks.ApiResult
import dev.sunnat629.openai_android.networks.postRequest
import io.ktor.client.HttpClient

interface ModerationsRepository {
    suspend fun createModeration(request: Any): ApiResult<Any>
}

class ModerationsRepositoryImpl(private val httpClient: HttpClient) : ModerationsRepository {

    private val baseUrl = "https://api.openai.com/v1/moderations"

    override suspend fun createModeration(request: Any): ApiResult<Any> {
        return httpClient.postRequest(
            url = baseUrl,
            request = request
        )
    }
}