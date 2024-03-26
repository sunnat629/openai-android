package dev.sunnat629.openai_android.audio

class CreateAudioSpeechUseCase(private val repository: AudioRepository) {
    suspend operator fun invoke(): MockResponse = repository.createAudioSpeech()
}

class CreateAudioTranscriptionUseCase(private val repository: AudioRepository) {
    suspend operator fun invoke(): MockResponse = repository.createAudioTranscription()
}

class CreateAudioTranslationUseCase(private val repository: AudioRepository) {
    suspend operator fun invoke(): MockResponse = repository.createAudioTranslation()
}