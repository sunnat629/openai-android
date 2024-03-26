package dev.sunnat629.openai_android.fineTuning

class CreateFineTuningJobUseCase(private val repository: FineTuningRepository) {
    suspend operator fun invoke(): MockResponse = repository.createFineTuningJob()
}

class ListFineTuningJobsUseCase(private val repository: FineTuningRepository) {
    suspend operator fun invoke(): MockResponse = repository.listFineTuningJobs()
}

class GetFineTuningJobDetailsUseCase(private val repository: FineTuningRepository, private val fineTuningJobId: String) {
    suspend operator fun invoke(): MockResponse = repository.getFineTuningJobDetails(fineTuningJobId)
}

class GetFineTuningJobEventsUseCase(private val repository: FineTuningRepository, private val fineTuningJobId: String) {
    suspend operator fun invoke(): MockResponse = repository.getFineTuningJobEvents(fineTuningJobId)
}

class CancelFineTuningJobUseCase(private val repository: FineTuningRepository, private val fineTuningJobId: String) {
    suspend operator fun invoke(): MockResponse = repository.cancelFineTuningJob(fineTuningJobId)
}