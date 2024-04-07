/**
 * @author  Mohi Us Sunnat
 * @date    01.04.24
 * Copyright ©2024 Sunnat629.dev. All rights reserved.
 */

package dev.sunnat629.openai_client

import dev.sunnat629.openai_client.KoinModules.openAiAndroidLibModuleKoin
import dev.sunnat629.openai_client.clients.Audio
import dev.sunnat629.openai_client.clients.Chat
import dev.sunnat629.openai_client.clients.Models
import dev.sunnat629.openai_client.clients.Moderations
import dev.sunnat629.openai_client.models.openaAI.OpenAIBuilderConfig
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface OpenAI {

    val chat: Chat
    val models: Models
    val moderations: Moderations
    val audio: Audio
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

    init {
        openAiAndroidLibModuleKoin(configModel)
    }
}