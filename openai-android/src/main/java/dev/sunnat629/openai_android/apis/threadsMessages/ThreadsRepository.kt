package dev.sunnat629.openai_android.apis.threadsMessages

import dev.sunnat629.openai_android.models.threadsMessages.CreateThreadRequest
import dev.sunnat629.openai_android.models.threadsMessages.ThreadResponse
import dev.sunnat629.openai_android.networks.ApiResult
import dev.sunnat629.openai_android.networks.getRequest
import dev.sunnat629.openai_android.networks.postRequest
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.post

// ThreadRepository.kt
interface ThreadRepository {
    /** Creates a new thread */
    suspend fun createThread(request: CreateThreadRequest): ApiResult<ThreadResponse>
    /** Lists all threads */
    suspend fun listThreads(): ApiResult<List<ThreadResponse>>
    /** Retrieves a thread by ID */
    suspend fun retrieveThread(threadId: String): ApiResult<ThreadResponse>
}

class ThreadRepositoryImpl(private val httpClient: HttpClient) : ThreadRepository {
    override suspend fun createThread(request: CreateThreadRequest): ApiResult<ThreadResponse> {
        return httpClient.postRequest(
            url = "https://api.openai.com/v1/threads",
            request = request
        )
    }

    override suspend fun listThreads(): ApiResult<List<ThreadResponse>> {
        return httpClient.getRequest(
            url = "https://api.openai.com/v1/threads"
        )
    }

    override suspend fun retrieveThread(threadId: String): ApiResult<ThreadResponse> {
        return httpClient.getRequest(
            url = "https://api.openai.com/v1/threads/$threadId"
        )
    }
}
