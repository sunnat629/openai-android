/**
 * @author  Mohi Us Sunnat
 * @date    01.04.24
 * Copyright ©2024 Sunnat629.dev. All rights reserved.
 */

package dev.sunnat629.openai_client

import dev.sunnat629.openai_client.KoinModules.openAiAndroidLibModuleKoin
import dev.sunnat629.openai_client.clients.chats.Chat
import dev.sunnat629.openai_client.clients.models.Models
import dev.sunnat629.openai_client.models.openaAI.OpenAIBuilderConfig
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface OpenAI {

    val chat: Chat
    val models: Models
}

internal class OpenAIImpl(configModel: OpenAIBuilderConfig) : OpenAI, KoinComponent {

    private val _chat: Chat by inject()
    override val chat: Chat get() = _chat

    private val _models: Models by inject()
    override val models: Models get() = _models

    init {
        openAiAndroidLibModuleKoin(configModel)
    }
}