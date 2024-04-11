/**
 * @author  Mohi Us Sunnat
 * @date    01.04.24
 * Copyright ©2024 Sunnat629.dev. All rights reserved.
 */

package dev.sunnat629.ai_client.apis.files

import dev.sunnat629.ai_client.models.files.FileResponse
import dev.sunnat629.ai_client.models.files.ListFilesResponse
import dev.sunnat629.ai_client.models.files.UploadFileRequest

/**
 * Interface defining operations for managing files with the OpenAI API.
 */
interface FilesRepository {

    /**
     * Uploads a file to the OpenAI API for use with various services like fine-tuning.
     *
     * @param request The require contents for the API
     * @return FileResponse Result of the file upload operation.
     */
    suspend fun uploadFile(request: UploadFileRequest, byteArray: ByteArray?): FileResponse

    /**
     * Retrieves a list of files previously uploaded to the OpenAI API.
     *
     * @return ListFilesResponse A list of files.
     */
    suspend fun listFiles(): ListFilesResponse

    /**
     * Retrieves a specific file by its unique identifier.
     *
     * @param fileId The unique identifier of the file to retrieve.
     * @return FileResponse The requested file.
     */
    suspend fun retrieveFile(fileId: String): FileResponse

    /**
     * Deletes a specific file by its unique identifier.
     *
     * @param fileId The unique identifier of the file to delete.
     * @return FileResponse The result of the deletion operation.
     */
    suspend fun deleteFile(fileId: String): FileResponse
}