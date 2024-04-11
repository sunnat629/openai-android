/**
 * @author  Mohi Us Sunnat
 * @date    01.04.24
 * Copyright ©2024 Sunnat629.dev. All rights reserved.
 */

package dev.sunnat629.ai_client.di

import dev.sunnat629.ai_client.OpenAI
import dev.sunnat629.ai_client.OpenAIImpl
import dev.sunnat629.ai_client.apis.assistants.AssistantRepository
import dev.sunnat629.ai_client.apis.assistants.AssistantRepositoryImpl
import dev.sunnat629.ai_client.apis.audio.AudioRepository
import dev.sunnat629.ai_client.apis.audio.AudioRepositoryImpl
import dev.sunnat629.ai_client.apis.chats.ChatRepository
import dev.sunnat629.ai_client.apis.chats.ChatRepositoryImpl
import dev.sunnat629.ai_client.apis.embeddings.EmbeddingsRepository
import dev.sunnat629.ai_client.apis.embeddings.EmbeddingsRepositoryImpl
import dev.sunnat629.ai_client.apis.files.FilesRepository
import dev.sunnat629.ai_client.apis.files.FilesRepositoryImpl
import dev.sunnat629.ai_client.apis.fineTuning.FineTuningRepository
import dev.sunnat629.ai_client.apis.fineTuning.FineTuningRepositoryImpl
import dev.sunnat629.ai_client.apis.images.ImageRepository
import dev.sunnat629.ai_client.apis.images.ImageRepositoryImpl
import dev.sunnat629.ai_client.apis.messages.MessageRepository
import dev.sunnat629.ai_client.apis.messages.MessageRepositoryImpl
import dev.sunnat629.ai_client.apis.models.ModelsRepository
import dev.sunnat629.ai_client.apis.models.ModelsRepositoryImpl
import dev.sunnat629.ai_client.apis.moderation.ModerationsRepository
import dev.sunnat629.ai_client.apis.moderation.ModerationsRepositoryImpl
import dev.sunnat629.ai_client.apis.threads.ThreadRepository
import dev.sunnat629.ai_client.apis.threads.ThreadRepositoryImpl
import dev.sunnat629.ai_client.clients.Audio
import dev.sunnat629.ai_client.clients.AudioImpl
import dev.sunnat629.ai_client.clients.Chat
import dev.sunnat629.ai_client.clients.ChatImpl
import dev.sunnat629.ai_client.clients.Embeddings
import dev.sunnat629.ai_client.clients.EmbeddingsImpl
import dev.sunnat629.ai_client.clients.File
import dev.sunnat629.ai_client.clients.FileImpl
import dev.sunnat629.ai_client.clients.Models
import dev.sunnat629.ai_client.clients.ModelsImpl
import dev.sunnat629.ai_client.clients.Moderations
import dev.sunnat629.ai_client.clients.ModerationsImpl
import dev.sunnat629.ai_client.models.openaAI.OpenAIBuilderConfig
import dev.sunnat629.ai_client.networks.ktorHttpClient
import io.ktor.client.HttpClient
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.dsl.module

object KoinModules {
    private var isKoinInitialized = false
    fun openAiAndroidLibModuleKoin(configModel: OpenAIBuilderConfig) {
        // This starts Koin for your library. You might call this from your library's initialization logic,
        // or provide it for the host app to call at an appropriate time.

        if (isKoinInitialized) return
        startKoin {
            // App declares its own modules, plus the library's modules
            modules(
                listOf(
                    openAIModule,
                    useCaseModule,
                    repositoryModule,
                    jsonModule,
                    ktorHttpClientModule(configModel),
                )
            )
        }
        isKoinInitialized = true
    }

    private fun ktorHttpClientModule(configModel: OpenAIBuilderConfig) = module {
        single<HttpClient> { ktorHttpClient(configModel, get()) }
    }

    private val jsonModule = module {
        single {
            Json {
                isLenient = true
                ignoreUnknownKeys = true
            }
        }
    }


    private val openAIModule = module {
        single<OpenAI> { OpenAIImpl(get()) }
    }

    private val useCaseModule = module {
        factory<Audio> { AudioImpl(get()) }
        factory<Chat> { ChatImpl(get()) }
        factory<Embeddings> { EmbeddingsImpl(get()) }
        factory<File> { FileImpl(get()) }
        factory<Models> { ModelsImpl(get()) }
        factory<Moderations> { ModerationsImpl(get()) }
    }

    private val repositoryModule = module {

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
}