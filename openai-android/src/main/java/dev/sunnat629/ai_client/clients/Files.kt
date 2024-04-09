/**
 * @author  Mohi Us Sunnat
 * @date    07.04.24
 * Copyright ©2024 Sunnat629.dev. All rights reserved.
 */

package dev.sunnat629.ai_client.clients

import dev.sunnat629.ai_client.models.files.FileResponse
import dev.sunnat629.ai_client.models.files.ListFilesResponse
import dev.sunnat629.ai_client.models.files.UploadFileRequest

interface Files {

    suspend fun uploadFile(file: FileResponse): UploadFileRequest
    suspend fun listFiles(): ListFilesResponse
    suspend fun retrieveFile(fileId: String): FileResponse
    suspend fun deleteFile(fileId: String): FileResponse
}