/**
 * @author  Mohi Us Sunnat
 * @date    01.04.24
 * Copyright ©2024 Sunnat629.dev. All rights reserved.
 */

package dev.sunnat629.ai_client.apis.messages

import dev.sunnat629.ai_client.models.messages.CreateMessageRequest
import dev.sunnat629.ai_client.models.messages.ListMessageFilesResponse
import dev.sunnat629.ai_client.models.messages.MessageFileDetails
import dev.sunnat629.ai_client.models.messages.MessageResponse


// MessageRepository.kt
interface MessageRepository {
    /** Creates a new message in a thread */
    suspend fun createMessage(
        threadId: String,
        request: CreateMessageRequest
    ): MessageResponse

    /** Lists all messages in a thread */
    suspend fun listMessages(threadId: String): List<MessageResponse>

    /** Retrieves a message by ID */
    suspend fun retrieveMessage(threadId: String, messageId: String): MessageResponse

    /**
     * Lists all files associated with a specific message in a thread.
     *
     * @param threadId The unique identifier of the thread containing the message.
     * @param messageId The unique identifier of the message associated with the files.
     * @return A list of file details.
     */
    suspend fun listMessageFiles(
        threadId: String,
        messageId: String
    ): ListMessageFilesResponse

    /**
     * Retrieves details for a specific file associated with a message in a thread.
     *
     * Note: If the API returns the file content directly for retrieval,
     * this method might need to return a different type (e.g., ByteArray for binary data).
     *
     * @param threadId The unique identifier of the thread containing the message.
     * @param messageId The unique identifier of the message associated with the file.
     * @param fileId The unique identifier of the file to retrieve.
     * @return The details of the specified file.
     */
    suspend fun retrieveMessageFile(
        threadId: String,
        messageId: String,
        fileId: String
    ): MessageFileDetails

}