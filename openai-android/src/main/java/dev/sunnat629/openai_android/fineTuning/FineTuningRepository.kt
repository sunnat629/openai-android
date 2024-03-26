package dev.sunnat629.openai_android.fineTuning

data class MockResponse(val result: String)

interface FineTuningRepository {

    // Fine-Tuning Operations
    suspend fun createFineTuningJob(): MockResponse
    suspend fun listFineTuningJobs(): MockResponse
    suspend fun getFineTuningJobDetails(fineTuningJobId: String): MockResponse
    suspend fun getFineTuningJobEvents(fineTuningJobId: String): MockResponse
    suspend fun cancelFineTuningJob(fineTuningJobId: String): MockResponse
}