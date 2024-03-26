package dev.sunnat629.openai_android.embeddings

class CreateAudioSpeechUseCase(private val repository: OpenAIRepository) {
    suspend operator fun invoke(): MockResponse = repository.createAudioSpeech()
}

class CreateAudioTranscriptionUseCase(private val repository: OpenAIRepository) {
    suspend operator fun invoke(): MockResponse = repository.createAudioTranscription()
}

class CreateAudioTranslationUseCase(private val repository: OpenAIRepository) {
    suspend operator fun invoke(): MockResponse = repository.createAudioTranslation()
}

class CreateChatCompletionUseCase(private val repository: OpenAIRepository) {
    suspend operator fun invoke(): MockResponse = repository.createChatCompletion()
}

class CreateEmbeddingUseCase(private val repository: OpenAIRepository) {
    suspend operator fun invoke(): MockResponse = repository.createEmbedding()
}

class CreateFineTuningJobUseCase(private val repository: OpenAIRepository) {
    suspend operator fun invoke(): MockResponse = repository.createFineTuningJob()
}

class ListFineTuningJobsUseCase(private val repository: OpenAIRepository) {
    suspend operator fun invoke(): MockResponse = repository.listFineTuningJobs()
}

class GetFineTuningJobDetailsUseCase(private val repository: OpenAIRepository, private val fineTuningJobId: String) {
    suspend operator fun invoke(): MockResponse = repository.getFineTuningJobDetails(fineTuningJobId)
}

class GetFineTuningJobEventsUseCase(private val repository: OpenAIRepository, private val fineTuningJobId: String) {
    suspend operator fun invoke(): MockResponse = repository.getFineTuningJobEvents(fineTuningJobId)
}

class CancelFineTuningJobUseCase(private val repository: OpenAIRepository, private val fineTuningJobId: String) {
    suspend operator fun invoke(): MockResponse = repository.cancelFineTuningJob(fineTuningJobId)
}

class UploadFileUseCase(private val repository: OpenAIRepository) {
    suspend operator fun invoke(): MockResponse = repository.uploadFile()
}

class ListFilesUseCase(private val repository: OpenAIRepository) {
    suspend operator fun invoke(): MockResponse = repository.listFiles()
}

class GetFileDetailsUseCase(private val repository: OpenAIRepository, private val fileId: String) {
    suspend operator fun invoke(): MockResponse = repository.getFileDetails(fileId)
}

class DeleteFileUseCase(private val repository: OpenAIRepository, private val fileId: String) {
    suspend operator fun invoke(): MockResponse = repository.deleteFile(fileId)
}

class GetFileContentUseCase(private val repository: OpenAIRepository, private val fileId: String) {
    suspend operator fun invoke(): MockResponse = repository.getFileContent(fileId)
}

class CreateImageGenerationUseCase(private val repository: OpenAIRepository) {
    suspend operator fun invoke(): MockResponse = repository.createImageGeneration()
}

class CreateImageEditUseCase(private val repository: OpenAIRepository) {
    suspend operator fun invoke(): MockResponse = repository.createImageEdit()
}

class CreateImageVariationUseCase(private val repository: OpenAIRepository) {
    suspend operator fun invoke(): MockResponse = repository.createImageVariation()
}

class ListModelsUseCase(private val repository: OpenAIRepository) {
    suspend operator fun invoke(): MockResponse = repository.listModels()
}

class GetModelDetailsUseCase(private val repository: OpenAIRepository, private val modelId: String) {
    suspend operator fun invoke(): MockResponse = repository.getModelDetails(modelId)
}

class DeleteModelUseCase(private val repository: OpenAIRepository, private val modelId: String) {
    suspend operator fun invoke(): MockResponse = repository.deleteModel(modelId)
}

class CreateModerationUseCase(private val repository: OpenAIRepository) {
    suspend operator fun invoke(): MockResponse = repository.createModeration()
}

class CreateAssistantUseCase(private val repository: OpenAIRepository) {
    suspend operator fun invoke(): MockResponse = repository.createAssistant()
}

class ListAssistantsUseCase(private val repository: OpenAIRepository) {
    suspend operator fun invoke(): MockResponse = repository.listAssistants()
}

class GetAssistantDetailsUseCase(private val repository: OpenAIRepository, private val assistantId: String) {
    suspend operator fun invoke(): MockResponse = repository.getAssistantDetails(assistantId)
}

class DeleteAssistantUseCase(private val repository: OpenAIRepository, private val assistantId: String) {
    suspend operator fun invoke(): MockResponse = repository.deleteAssistant(assistantId)
}

class CreateThreadUseCase(private val repository: OpenAIRepository, private val assistantId: String) {
    suspend operator fun invoke(): MockResponse = repository.createThread(assistantId)
}

class GetThreadDetailsUseCase(private val repository: OpenAIRepository, private val assistantId: String, private val threadId: String) {
    suspend operator fun invoke(): MockResponse = repository.getThreadDetails(assistantId, threadId)
}

class CreateMessageUseCase(private val repository: OpenAIRepository, private val assistantId: String, private val threadId: String) {
    suspend operator fun invoke(): MockResponse = repository.createMessage(assistantId, threadId)
}

class GetMessageDetailsUseCase(private val repository: OpenAIRepository, private val assistantId: String, private val threadId: String, private val messageId: String) {
    suspend operator fun invoke(): MockResponse = repository.getMessageDetails(assistantId, threadId, messageId)
}