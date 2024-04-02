/**
 * @author  Mohi Us Sunnat
 * @date    01.04.24
 * Copyright ©2024 Sunnat629.dev. All rights reserved.
 */

package dev.sunnat629.openai_client.apis.files

import dev.sunnat629.openai_client.models.files.FileResponse
import dev.sunnat629.openai_client.models.files.ListFilesResponse
import dev.sunnat629.openai_client.models.files.UploadFileRequest
import dev.sunnat629.openai_client.networks.ApiResult

/**
 * Interface defining operations for managing files with the OpenAI API.
 */
interface FilesRepository {

    /**
     * Uploads a file to the OpenAI API for use with various services like fine-tuning.
     *
     * @param file The file to be uploaded.
     * @return ApiResult<UploadFileRequest> Result of the file upload operation.
     */
    suspend fun uploadFile(file: FileResponse): ApiResult<UploadFileRequest>

    /**
     * Retrieves a list of files previously uploaded to the OpenAI API.
     *
     * @return ApiResult<ListFilesResponse> A list of files.
     */
    suspend fun listFiles(): ApiResult<ListFilesResponse>

    /**
     * Retrieves a specific file by its unique identifier.
     *
     * @param fileId The unique identifier of the file to retrieve.
     * @return ApiResult<FileResponse> The requested file.
     */
    suspend fun retrieveFile(fileId: String): ApiResult<FileResponse>

    /**
     * Deletes a specific file by its unique identifier.
     *
     * @param fileId The unique identifier of the file to delete.
     * @return ApiResult<FileResponse> The result of the deletion operation.
     */
    suspend fun deleteFile(fileId: String): ApiResult<FileResponse>
}