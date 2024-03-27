package dev.sunnat629.openai_android.apis.assistants

class CreateAssistantUseCase(private val repository: AssistantsRepository) {
    suspend operator fun invoke(): MockResponse = repository.createAssistant()
}

class ListAssistantsUseCase(private val repository: AssistantsRepository) {
    suspend operator fun invoke(): MockResponse = repository.listAssistants()
}

class GetAssistantDetailsUseCase(
    private val repository: AssistantsRepository,
    private val assistantId: String
) {
    suspend operator fun invoke(): MockResponse = repository.getAssistantDetails(assistantId)
}

class DeleteAssistantUseCase(
    private val repository: AssistantsRepository,
    private val assistantId: String
) {
    suspend operator fun invoke(): MockResponse = repository.deleteAssistant(assistantId)
}