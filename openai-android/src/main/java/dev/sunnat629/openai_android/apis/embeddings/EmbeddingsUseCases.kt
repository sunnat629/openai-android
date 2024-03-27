package dev.sunnat629.openai_android.apis.embeddings

class CreateEmbeddingUseCase(private val repository: EmbeddingsRepository) {
    suspend operator fun invoke(): MockResponse =
        repository.createEmbedding()
}