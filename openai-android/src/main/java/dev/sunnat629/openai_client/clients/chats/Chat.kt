/**
 * @author  Mohi Us Sunnat
 * @date    01.04.24
 * Copyright ©2024 Sunnat629.dev. All rights reserved.
 */

package dev.sunnat629.openai_client.clients.chats

import dev.sunnat629.openai_client.apis.chats.ChatRepository
import dev.sunnat629.openai_client.clients.BaseUseCases
import dev.sunnat629.openai_client.models.chats.ChatContent
import dev.sunnat629.openai_client.models.chats.ChatMessage
import dev.sunnat629.openai_client.models.chats.ChatRequest
import dev.sunnat629.openai_client.models.chats.ChatResponse
import dev.sunnat629.openai_client.models.chats.ImageContent
import dev.sunnat629.openai_client.models.chats.TextContent
import dev.sunnat629.openai_client.networks.ApiResult

interface Chat : BaseUseCases<Chat> {

    suspend fun create(): ApiResult<ChatResponse>
}

internal class ChatImpl(private val repository: ChatRepository) : Chat {

    private var _model: String? = null
    private var _role: String? = null
    private var _text: String? = null
    private var _imageUrl: String? = null

    override fun model(model: String): Chat {
        this._model = model
        return this
    }

    override fun role(role: String): Chat {
        this._role = role
        return this
    }

    override fun text(text: String): Chat {
        this._text = text
        return this
    }

    override fun imageUrl(imageUrl: String): Chat {
        this._imageUrl = imageUrl
        return this
    }

    override suspend fun create(): ApiResult<ChatResponse> {
        // Initialize an empty list for content
        val contentList = mutableListOf<ChatContent>()

        // Add ImageContent to the list if _imageUrl is not null or empty
        if (!_imageUrl.isNullOrEmpty()) {
            contentList.add(ImageContent(imageUrl = _imageUrl!!))
        }

        // Add TextContent to the list if _text is not null or empty
        if (!_text.isNullOrEmpty()) {
            contentList.add(TextContent(text = _text!!))
        }

        // Create the ChatRequest object using the dynamically built content list
        val request = ChatRequest(
            model = _model!!,
            messages = listOf(
                ChatMessage(
                    role = _role!!,
                    content = contentList
                )
            )
        )

        // Pass the request to the repository
        return repository.chat(request)
    }
}