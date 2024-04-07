/**
 * @author  Mohi Us Sunnat
 * @date    01.04.24
 * Copyright ©2024 Sunnat629.dev. All rights reserved.
 */

package dev.sunnat629.ai_client.clients

import dev.sunnat629.ai_client.apis.moderation.ModerationsRepository
import dev.sunnat629.ai_client.models.moderation.Input
import dev.sunnat629.ai_client.models.moderation.ModerationRequest
import dev.sunnat629.ai_client.models.moderation.ModerationResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * The Moderations interface provides functionalities for classifying text to determine if it is potentially harmful.
 * It supports submitting text input for moderation and initiating the classification process.
 */
interface Moderations {

    /**
     * Sets the input text to be classified by the moderation system.
     * The input can be a single string or an array of strings, each representing a piece of content to be moderated.
     *
     * @param input The text input for moderation. It can be a single string or a list of strings.
     * @return An instance of Moderations for method chaining.
     */
    fun input(input: Any): Moderations

    /**
     * Initiates the moderation process for the provided input.
     * This method is asynchronous and returns a Flow emitting ModerationResponse objects,
     * which contain the moderation results, including flagged status and category scores.
     *
     * @return A Flow emitting ModerationResponse objects with the moderation results.
     */
    suspend fun moderate(): Flow<ModerationResponse>
}

class ModerationsImpl(private val repository: ModerationsRepository) : Moderations {

    private var _input: Any? = null

    override fun input(input: Any): Moderations {
        this._input = input
        return this
    }

    override suspend fun moderate(): Flow<ModerationResponse> {
        val request = ModerationRequest(
            when (_input) {
                is String -> Input.Single(_input as String)
                is List<*> -> {
                    val list = if ((_input as List<*>).all { it is String }) {
                        (_input as List<*>).filterIsInstance<String>()
                    } else throw NullPointerException("Input is null")

                    Input.Multiple(list)
                }

                else -> throw NullPointerException("Input is null")
            }
        )

        return flow { emit(repository.createModeration(request)) }
    }
}