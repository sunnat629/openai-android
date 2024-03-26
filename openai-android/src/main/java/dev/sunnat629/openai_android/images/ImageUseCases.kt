package dev.sunnat629.openai_android.images

class CreateImageGenerationUseCase(private val repository: ImageRepository) {
    suspend operator fun invoke(): MockResponse = repository.createImageGeneration()
}

class CreateImageEditUseCase(private val repository: ImageRepository) {
    suspend operator fun invoke(): MockResponse = repository.createImageEdit()
}

class CreateImageVariationUseCase(private val repository: ImageRepository) {
    suspend operator fun invoke(): MockResponse = repository.createImageVariation()
}