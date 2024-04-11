/**
 * @author  Mohi Us Sunnat
 * @date    01.04.24
 * Copyright ©2024 Sunnat629.dev. All rights reserved.
 */

package dev.sunnat629.ai_client

import dev.sunnat629.ai_client.di.KoinModules.openAiAndroidLibModuleKoin
import dev.sunnat629.ai_client.clients.Audio
import dev.sunnat629.ai_client.clients.Chat
import dev.sunnat629.ai_client.clients.Embeddings
import dev.sunnat629.ai_client.clients.File
import dev.sunnat629.ai_client.clients.Models
import dev.sunnat629.ai_client.clients.Moderations
import dev.sunnat629.ai_client.models.openaAI.OpenAIBuilderConfig
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface OpenAI {

    val chat: Chat
    val models: Models
    val moderations: Moderations
    val audio: Audio
    val embeddings: Embeddings
    val file: File
}

internal class OpenAIImpl(configModel: OpenAIBuilderConfig) : OpenAI, KoinComponent {

    private val _chat: Chat by inject()
    override val chat: Chat get() = _chat

    private val _models: Models by inject()
    override val models: Models get() = _models

    private val _moderations: Moderations by inject()
    override val moderations: Moderations get() = _moderations

    private val _audio: Audio by inject()
    override val audio: Audio get() = _audio

    private val _embeddings: Embeddings by inject()
    override val embeddings: Embeddings get() = _embeddings

    private val _file: File by inject()
    override val file: File get() = _file

    init {
        openAiAndroidLibModuleKoin(configModel)
    }
}