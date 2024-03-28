package dev.sunnat629.openai_android.apis.chats

class CreateChatCompletionUseCase(private val repository: ChatRepository) {
    suspend operator fun invoke(): MockResponse =
        repository.createChatCompletion()
}