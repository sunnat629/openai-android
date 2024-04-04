/**
 * @author  Mohi Us Sunnat
 * @date    01.04.24
 * Copyright ©2024 Sunnat629.dev. All rights reserved.
 */

package dev.sunnat629.openai_client.apis.moderation

import dev.sunnat629.openai_client.models.moderation.ModerationRequest
import dev.sunnat629.openai_client.models.moderation.ModerationResponse

/**
 * The ModerationsRepository interface abstracts the data layer for moderation requests.
 * It defines how moderation requests are processed and handled within the system.
 */
interface ModerationsRepository {

    /**
     * Creates a moderation request to classify the provided text input as potentially harmful or not.
     * It accepts a ModerationRequest object containing the input text and optionally the model to be used for classification.
     * The function is suspendable, indicating it performs an asynchronous operation and returns a ModerationResponse.
     *
     * @param input A ModerationRequest object containing the text to be moderated and the model specification.
     * @return A ModerationResponse object containing the results of the moderation, such as flagged status and category scores.
     */
    suspend fun createModeration(input: ModerationRequest): ModerationResponse
}