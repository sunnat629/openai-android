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
import dev.sunnat629.openai_client.networks.deleteRequest
import dev.sunnat629.openai_client.networks.getRequest
import io.ktor.client.HttpClient

class FilesRepositoryImpl(private val httpClient: HttpClient) : FilesRepository {
    override suspend fun uploadFile(file: FileResponse): ApiResult<UploadFileRequest> {
        // Implement the upload logic, potentially handling multipart/form-data for file upload
        return ApiResult.Failure(NotImplementedError())
    }

    override suspend fun listFiles(): ApiResult<ListFilesResponse> {
        return httpClient.getRequest(
            url = "https://api.openai.com/v1/files"
        )
    }

    override suspend fun retrieveFile(fileId: String): ApiResult<FileResponse> {
        return httpClient.getRequest(
            url = "https://api.openai.com/v1/files/$fileId"
        )
    }

    override suspend fun deleteFile(fileId: String): ApiResult<FileResponse> {
        return httpClient.deleteRequest(
            url = "https://api.openai.com/v1/files/$fileId"
        )
    }
}