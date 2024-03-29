package dev.sunnat629.openai_android.apis.moderation

class CreateModerationUseCase(private val repository: ModerationRepository) {
    suspend operator fun invoke(): MockResponse =
        repository.createModeration()
}