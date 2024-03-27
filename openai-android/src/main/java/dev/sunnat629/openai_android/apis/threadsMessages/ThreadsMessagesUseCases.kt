package dev.sunnat629.openai_android.apis.threadsMessages

class CreateThreadUseCase(
    private val repository: dev.sunnat629.openai_android.apis.threadsMessages.OpenAIRepository,
    private val assistantId: String
) {
    suspend operator fun invoke(): MockResponse =
        repository.createThread(assistantId)
}

class GetThreadDetailsUseCase(
    private val repository: dev.sunnat629.openai_android.apis.threadsMessages.OpenAIRepository,
    private val assistantId: String,
    private val threadId: String
) {
    suspend operator fun invoke(): MockResponse =
        repository.getThreadDetails(assistantId, threadId)
}

class CreateMessageUseCase(
    private val repository: dev.sunnat629.openai_android.apis.threadsMessages.OpenAIRepository,
    private val assistantId: String,
    private val threadId: String
) {
    suspend operator fun invoke(): MockResponse =
        repository.createMessage(assistantId, threadId)
}

class GetMessageDetailsUseCase(
    private val repository: dev.sunnat629.openai_android.apis.threadsMessages.OpenAIRepository,
    private val assistantId: String,
    private val threadId: String,
    private val messageId: String
) {
    suspend operator fun invoke(): MockResponse =
        repository.getMessageDetails(assistantId, threadId, messageId)
}