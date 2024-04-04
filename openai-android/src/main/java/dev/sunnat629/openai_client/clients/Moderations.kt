/**
 * @author  Mohi Us Sunnat
 * @date    01.04.24
 * Copyright ©2024 Sunnat629.dev. All rights reserved.
 */

package dev.sunnat629.openai_client.clients

import dev.sunnat629.openai_client.apis.chats.ChatRepositoryImpl
import dev.sunnat629.openai_client.apis.moderation.ModerationsRepository
import dev.sunnat629.openai_client.apis.moderation.ModerationsRepositoryImpl
import dev.sunnat629.openai_client.models.models.ListModelsResponse
import dev.sunnat629.openai_client.models.models.ModelResponse
import dev.sunnat629.openai_client.models.moderation.ModerationResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

interface Moderations {

    fun input(input: Any): Moderations
    suspend fun moderate(): Flow<ModerationResponse>
}

class ModerationsImpl(private val repository: ModerationsRepository): Moderations {

    private var _input: Any? = null

    override fun input(input: Any): Moderations {
        this._input = input
        return this
    }

    override suspend fun moderate(): Flow<ModerationResponse> {
        if (_input == null) throw NullPointerException("Input is null")
        return  flow { emit(repository.createModeration(_input!!)) }
    }
}