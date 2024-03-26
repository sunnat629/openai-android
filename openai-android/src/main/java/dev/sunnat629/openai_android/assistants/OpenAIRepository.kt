package dev.sunnat629.openai_android.assistants

data class MockResponse(val result: String)

interface OpenAIRepository {
    // Audio Operations
    suspend fun createAudioSpeech(): MockResponse
    suspend fun createAudioTranscription(): MockResponse
    suspend fun createAudioTranslation(): MockResponse

    // Chat Operations
    suspend fun createChatCompletion(): MockResponse

    // Embeddings Operations
    suspend fun createEmbedding(): MockResponse

    // Fine-Tuning Operations
    suspend fun createFineTuningJob(): MockResponse
    suspend fun listFineTuningJobs(): MockResponse
    suspend fun getFineTuningJobDetails(fineTuningJobId: String): MockResponse
    suspend fun getFineTuningJobEvents(fineTuningJobId: String): MockResponse
    suspend fun cancelFineTuningJob(fineTuningJobId: String): MockResponse

    // Files Operations
    suspend fun uploadFile(): MockResponse
    suspend fun listFiles(): MockResponse
    suspend fun getFileDetails(fileId: String): MockResponse
    suspend fun deleteFile(fileId: String): MockResponse
    suspend fun getFileContent(fileId: String): MockResponse

    // Images Operations
    suspend fun createImageGeneration(): MockResponse
    suspend fun createImageEdit(): MockResponse
    suspend fun createImageVariation(): MockResponse

    // Models Operations
    suspend fun listModels(): MockResponse
    suspend fun getModelDetails(modelId: String): MockResponse
    suspend fun deleteModel(modelId: String): MockResponse

    // Moderation Operations
    suspend fun createModeration(): MockResponse

    // Assistants Operations
    suspend fun createAssistant(): MockResponse
    suspend fun listAssistants(): MockResponse
    suspend fun getAssistantDetails(assistantId: String): MockResponse
    suspend fun deleteAssistant(assistantId: String): MockResponse

    // Threads and Messages Operations
    suspend fun createThread(assistantId: String): MockResponse
    suspend fun getThreadDetails(assistantId: String, threadId: String): MockResponse
    suspend fun createMessage(assistantId: String, threadId: String): MockResponse
    suspend fun getMessageDetails(assistantId: String, threadId: String, messageId: String): MockResponse
}
