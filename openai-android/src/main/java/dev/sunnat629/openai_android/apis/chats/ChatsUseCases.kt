package dev.sunnat629.openai_android.apis.chats

class CreateChatCompletionUseCase(private val repository: ChatsRepository) {
    suspend operator fun invoke(): MockResponse =
        repository.createChatCompletion()
}