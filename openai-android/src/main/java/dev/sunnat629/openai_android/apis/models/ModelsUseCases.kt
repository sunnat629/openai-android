package dev.sunnat629.openai_android.apis.models

class ListModelsUseCase(private val repository: ModelsRepository) {
    suspend operator fun invoke(): MockResponse =
        repository.listModels()
}

class GetModelDetailsUseCase(
    private val repository: ModelsRepository,
    private val modelId: String
) {
    suspend operator fun invoke(): MockResponse =
        repository.getModelDetails(modelId)
}

class DeleteModelUseCase(
    private val repository: ModelsRepository,
    private val modelId: String
) {
    suspend operator fun invoke(): MockResponse =
        repository.deleteModel(modelId)
}