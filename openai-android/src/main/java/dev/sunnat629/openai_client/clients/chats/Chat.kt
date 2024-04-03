/**
 * @author  Mohi Us Sunnat
 * @date    01.04.24
 * Copyright ©2024 Sunnat629.dev. All rights reserved.
 */

package dev.sunnat629.openai_client.clients.chats

import android.util.Log
import dev.sunnat629.openai_client.apis.chats.ChatRepository
import dev.sunnat629.openai_client.clients.BaseUseCases
import dev.sunnat629.openai_client.models.chats.ChatContent
import dev.sunnat629.openai_client.models.chats.ChatMessage
import dev.sunnat629.openai_client.models.chats.ChatRequest
import dev.sunnat629.openai_client.models.chats.ChatResponse
import dev.sunnat629.openai_client.models.chats.FunctionTool
import dev.sunnat629.openai_client.models.chats.ImageContent
import dev.sunnat629.openai_client.models.chats.TextContent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

interface Chat : BaseUseCases<Chat> {

    suspend fun stream(enable: Boolean, delay: Long = 200L): Chat
    suspend fun logprobs(enable: Boolean, topLogprobs: Int): Chat
    suspend fun maxTokens(maxTokens: Int): Chat
    suspend fun toolChoice(toolChoice: String): Chat
    suspend fun tools(tools: List<FunctionTool>): Chat

    fun create(): Flow<ChatResponse>
}

internal class ChatImpl(private val repository: ChatRepository) : Chat {

    private var _model: String? = null
    private var _role: String? = null
    private var _text: String? = null
    private var _imageUrl: String? = null
    private var _stream: Boolean? = null
    private var _delay: Long = 200
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

    override suspend fun stream(enable: Boolean, delay: Long): Chat {
        this._stream = enable
        this._delay = delay
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

    override fun create(): Flow<ChatResponse> {
        val contentList = mutableListOf<ChatContent>().apply {
            if (!_imageUrl.isNullOrEmpty()) {
                add(ImageContent(imageUrl = _imageUrl!!))
            }
            if (!_text.isNullOrEmpty()) {
                add(TextContent(text = _text!!))
            }
        }

        val request = ChatRequest(
            model = _model!!,
            messages = listOf(ChatMessage(role = _role!!, content = contentList)),
            stream = _stream,
            logprobs = _logprobs,
            maxTokens = _maxTokens,
            topLogprobs = _topLogprobs,
            toolChoice = _toolChoice,
            tools = _tools,
        )

        return when {
            _stream == true -> repository.createChatSteam(request, _delay)
            else -> flow { emit(repository.createChat(request)) }
        }
    }
}