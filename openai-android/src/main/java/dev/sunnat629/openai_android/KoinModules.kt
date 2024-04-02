/**
 * @author  Mohi Us Sunnat
 * @date    01.04.24
 * Copyright ©2024 Sunnat629.dev. All rights reserved.
 */

package dev.sunnat629.openai_android

import dev.sunnat629.openai_android.apis.assistants.AssistantRepository
import dev.sunnat629.openai_android.apis.assistants.AssistantRepositoryImpl
import dev.sunnat629.openai_android.apis.audio.AudioRepository
import dev.sunnat629.openai_android.apis.audio.AudioRepositoryImpl
import dev.sunnat629.openai_android.apis.chats.ChatRepository
import dev.sunnat629.openai_android.apis.chats.ChatRepositoryImpl
import dev.sunnat629.openai_android.apis.embeddings.EmbeddingsRepository
import dev.sunnat629.openai_android.apis.embeddings.EmbeddingsRepositoryImpl
import dev.sunnat629.openai_android.apis.files.FilesRepository
import dev.sunnat629.openai_android.apis.files.FilesRepositoryImpl
import dev.sunnat629.openai_android.apis.fineTuning.FineTuningRepository
import dev.sunnat629.openai_android.apis.fineTuning.FineTuningRepositoryImpl
import dev.sunnat629.openai_android.apis.images.ImageRepository
import dev.sunnat629.openai_android.apis.images.ImageRepositoryImpl
import dev.sunnat629.openai_android.apis.messages.MessageRepository
import dev.sunnat629.openai_android.apis.messages.MessageRepositoryImpl
import dev.sunnat629.openai_android.apis.models.ModelsRepository
import dev.sunnat629.openai_android.apis.models.ModelsRepositoryImpl
import dev.sunnat629.openai_android.apis.moderation.ModerationsRepository
import dev.sunnat629.openai_android.apis.moderation.ModerationsRepositoryImpl
import dev.sunnat629.openai_android.apis.threads.ThreadRepository
import dev.sunnat629.openai_android.apis.threads.ThreadRepositoryImpl
import dev.sunnat629.openai_android.clients.chats.Chat
import dev.sunnat629.openai_android.clients.chats.ChatImpl
import dev.sunnat629.openai_android.clients.models.Models
import dev.sunnat629.openai_android.clients.models.ModelsImpl
import dev.sunnat629.openai_android.models.openaAI.OpenAIBuilderConfig
import dev.sunnat629.openai_android.networks.openAiHttpClient
import io.ktor.client.HttpClient
import org.koin.core.context.startKoin
import org.koin.dsl.module

fun openAiAndroidLibModuleKoin(configModel: OpenAIBuilderConfig) {
    // This starts Koin for your library. You might call this from your library's initialization logic,
    // or provide it for the host app to call at an appropriate time.
    startKoin {
        // App declares its own modules, plus the library's modules
        modules(
            listOf(
                openAIModule,
                useCaseModule,
                openAiAndroidLibModule(configModel),
            )
        )
    }
}

val openAIModule = module {
    single<OpenAI> { OpenAIImpl(get()) }
}


val useCaseModule = module {
    factory<Chat> { ChatImpl(get()) }
    factory<Models> { ModelsImpl(get()) }
}

fun openAiAndroidLibModule(configModel: OpenAIBuilderConfig) = module {
    // Provide HttpClient
    single<HttpClient> { openAiHttpClient(configModel) }

    // Repository implementations
    single<AudioRepository> { AudioRepositoryImpl(get()) }
    single<ChatRepository> { ChatRepositoryImpl(get()) }
    single<EmbeddingsRepository> { EmbeddingsRepositoryImpl(get()) }
    single<FineTuningRepository> { FineTuningRepositoryImpl(get()) }
    single<FilesRepository> { FilesRepositoryImpl(get()) }
    single<ImageRepository> { ImageRepositoryImpl(get()) }
    single<ModelsRepository> { ModelsRepositoryImpl(get()) }
    single<ModerationsRepository> { ModerationsRepositoryImpl(get()) }
    single<AssistantRepository> { AssistantRepositoryImpl(get()) }
    single<ThreadRepository> { ThreadRepositoryImpl(get()) }
    single<MessageRepository> { MessageRepositoryImpl(get()) }
}