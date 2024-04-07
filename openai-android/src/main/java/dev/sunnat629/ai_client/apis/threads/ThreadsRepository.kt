/**
 * @author  Mohi Us Sunnat
 * @date    01.04.24
 * Copyright ©2024 Sunnat629.dev. All rights reserved.
 */

package dev.sunnat629.ai_client.apis.threads

import dev.sunnat629.ai_client.models.threads.CreateThreadRequest
import dev.sunnat629.ai_client.models.threads.ThreadResponse
import dev.sunnat629.ai_client.networks.ApiResult

// ThreadRepository.kt
interface ThreadRepository {
    /** Creates a new thread */
    suspend fun createThread(request: CreateThreadRequest): ApiResult<ThreadResponse>

    /** Lists all threads */
    suspend fun listThreads(): ApiResult<List<ThreadResponse>>

    /** Retrieves a thread by ID */
    suspend fun retrieveThread(threadId: String): ApiResult<ThreadResponse>
}