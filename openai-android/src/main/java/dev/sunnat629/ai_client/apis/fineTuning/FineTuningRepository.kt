/**
 * @author  Mohi Us Sunnat
 * @date    01.04.24
 * Copyright ©2024 Sunnat629.dev. All rights reserved.
 */

package dev.sunnat629.ai_client.apis.fineTuning

import dev.sunnat629.ai_client.models.fineTuning.CreateFineTuningRequest
import dev.sunnat629.ai_client.models.fineTuning.FineTuningResponse

// FineTuningRepository.kt
interface FineTuningRepository {
    /** Creates a fine-tuning job */
    suspend fun createFineTuningJob(request: CreateFineTuningRequest): FineTuningResponse

    /** Lists all fine-tuning jobs */
    suspend fun listFineTuningJobs(): List<FineTuningResponse>

    /** Retrieves a fine-tuning job by ID */
    suspend fun retrieveFineTuningJob(fineTuneId: String): FineTuningResponse

    /** Cancels a fine-tuning job */
    suspend fun cancelFineTuningJob(fineTuneId: String): Unit
}