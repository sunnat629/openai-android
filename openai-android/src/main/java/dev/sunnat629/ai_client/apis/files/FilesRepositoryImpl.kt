/**
 * @author  Mohi Us Sunnat
 * @date    01.04.24
 * Copyright ©2024 Sunnat629.dev. All rights reserved.
 */

package dev.sunnat629.ai_client.apis.files

import dev.sunnat629.ai_client.models.files.FileResponse
import dev.sunnat629.ai_client.models.files.ListFilesResponse
import dev.sunnat629.ai_client.models.files.UploadFileRequest
import dev.sunnat629.ai_client.networks.deleteRequest
import dev.sunnat629.ai_client.networks.getRequest
import io.ktor.client.HttpClient

class FilesRepositoryImpl(private val httpClient: HttpClient) : FilesRepository {

    override suspend fun uploadFile(file: FileResponse): UploadFileRequest {
        return UploadFileRequest("", "")
    }

    override suspend fun listFiles(): ListFilesResponse {
        return httpClient.getRequest(
            url = "https://api.openai.com/v1/files"
        )
    }

    override suspend fun retrieveFile(fileId: String): FileResponse {
        return httpClient.getRequest(
            url = "https://api.openai.com/v1/files/$fileId"
        )
    }

    override suspend fun deleteFile(fileId: String): FileResponse {
        return httpClient.deleteRequest(
            url = "https://api.openai.com/v1/files/$fileId"
        )
    }
}