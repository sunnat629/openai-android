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
import dev.sunnat629.openai_client.models.chats.FunctionTool
import dev.sunnat629.openai_client.models.chats.ImageContent
import dev.sunnat629.openai_client.models.chats.TextContent
import dev.sunnat629.openai_client.networks.ApiResult

interface Chat : BaseUseCases<Chat> {

    suspend fun stream(enable: Boolean): Chat
    suspend fun logprobs(enable: Boolean, topLogprobs: Int): Chat
    suspend fun maxTokens(maxTokens: Int): Chat
    suspend fun toolChoice(toolChoice: String): Chat
    suspend fun tools(tools: List<FunctionTool>): Chat

    suspend fun create(): ApiResult<ChatResponse>
}

internal class ChatImpl(private val repository: ChatRepository) : Chat {

    private var _model: String? = null
    private var _role: String? = null
    private var _text: String? = null
    private var _imageUrl: String? = null
    private var _stream: Boolean? = null
    private var _logprobs: Boolean? = null
    private var _topLogprobs: Int? = null
    private var _maxTokens: Int? = null
    private var _toolChoice: String? = null
    private var _tools: List<FunctionTool>? = null

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

    override suspend fun stream(enable: Boolean): Chat {
        this._stream = enable
        return this
    }

    override suspend fun logprobs(enable: Boolean, topLogprobs: Int): Chat {
        this._logprobs = enable
        this._topLogprobs = topLogprobs
        return this
    }

    override suspend fun maxTokens(maxTokens: Int): Chat {
        this._maxTokens = maxTokens
        return this
    }

    override suspend fun toolChoice(toolChoice: String): Chat {
        this._toolChoice = toolChoice
        return this
    }

    override suspend fun tools(tools: List<FunctionTool>): Chat {
        this._tools = tools
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
            ),
            stream = _stream,
            logprobs = _logprobs,
            maxTokens = _maxTokens,
            topLogprobs = _topLogprobs,
            toolChoice = _toolChoice,
            tools = _tools,
        )

        // Pass the request to the repository
        return repository.chat(request)
    }
}