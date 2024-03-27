package dev.sunnat629.openai_android

import dev.sunnat629.openai_android.apis.assistants.AssistantsRepository
import dev.sunnat629.openai_android.apis.assistants.AssistantsRepositoryImpl
import dev.sunnat629.openai_android.apis.audio.AudioRepository
import dev.sunnat629.openai_android.apis.audio.AudioRepositoryImpl
import dev.sunnat629.openai_android.apis.chats.ChatRepositoryImpl
import dev.sunnat629.openai_android.apis.chats.ChatsRepository
import dev.sunnat629.openai_android.apis.embeddings.EmbeddingsRepository
import dev.sunnat629.openai_android.apis.embeddings.EmbeddingsRepositoryImpl
import dev.sunnat629.openai_android.apis.files.FilesRepository
import dev.sunnat629.openai_android.apis.files.FilesRepositoryImpl
import dev.sunnat629.openai_android.apis.fineTuning.FineTuningRepository
import dev.sunnat629.openai_android.apis.fineTuning.FineTuningRepositoryImpl
import dev.sunnat629.openai_android.apis.images.ImageRepository
import dev.sunnat629.openai_android.apis.images.ImageRepositoryImpl
import dev.sunnat629.openai_android.apis.models.ModelsRepository
import dev.sunnat629.openai_android.apis.models.ModelsRepositoryImpl
import dev.sunnat629.openai_android.apis.moderation.ModerationRepository
import dev.sunnat629.openai_android.apis.moderation.ModerationRepositoryImpl
import dev.sunnat629.openai_android.apis.threadsMessages.ThreadsMessagesRepository
import dev.sunnat629.openai_android.apis.threadsMessages.ThreadsMessagesRepositoryImpl
import dev.sunnat629.openai_android.networks.KTorNetwork.provideHttpClient
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.dsl.module

fun openAiAndroidLibModuleKoin() {
    // This starts Koin for your library. You might call this from your library's initialization logic,
    // or provide it for the host app to call at an appropriate time.
    startKoin {
        // App declares its own modules, plus the library's modules
        modules(openAiAndroidLibModule)
    }
}

val openAiAndroidLibModule = module {
    // Provide HttpClient
    single<HttpClient> { provideHttpClient() }

    // Repository implementations
    single<AudioRepository> { AudioRepositoryImpl(get()) }
    single<ChatsRepository> { ChatRepositoryImpl(get()) }
    single<EmbeddingsRepository> { EmbeddingsRepositoryImpl(get()) }
    single<FineTuningRepository> { FineTuningRepositoryImpl(get()) }
    single<FilesRepository> { FilesRepositoryImpl(get()) }
    single<ImageRepository> { ImageRepositoryImpl(get()) }
    single<ModelsRepository> { ModelsRepositoryImpl(get()) }
    single<ModerationRepository> { ModerationRepositoryImpl(get()) }
    single<AssistantsRepository> { AssistantsRepositoryImpl(get()) }
    single<ThreadsMessagesRepository> { ThreadsMessagesRepositoryImpl(get()) }
}