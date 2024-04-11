/**
 * @author  Mohi Us Sunnat
 * @date    01.04.24
 * Copyright ©2024 Sunnat629.dev. All rights reserved.
 */

package dev.sunnat629.ai_client.apis.files

import android.util.Log
import dev.sunnat629.ai_client.models.files.FileResponse
import dev.sunnat629.ai_client.models.files.ListFilesResponse
import dev.sunnat629.ai_client.models.files.Purpose
import dev.sunnat629.ai_client.models.files.UploadFileRequest
import dev.sunnat629.ai_client.networks.deleteRequest
import dev.sunnat629.ai_client.networks.getRequest
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.forms.formData
import io.ktor.client.request.forms.submitFormWithBinaryData
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import io.ktor.util.InternalAPI

class FilesRepositoryImpl(private val client: HttpClient) : FilesRepository {

    @OptIn(InternalAPI::class)
    override suspend fun uploadFile(request: UploadFileRequest, byteArray: ByteArray?): FileResponse {

        val content: Any = when (request.purpose) {
            Purpose.FINE_TUNE -> request.fileJsonl
            Purpose.ASSISTANTS -> byteArray
        }

        val response = client.submitFormWithBinaryData(
            url = "files",
            formData = formData {
                append("purpose", request.purpose.value)
                append(
                    key = "file",
                    value = content,
                    headers = Headers.build {
                        append(HttpHeaders.ContentDisposition, "filename=file.jsonl")
                    }
                )
            }
        )

        Log.e("ASDF", response.body<FileResponse>().toString())

        return response.body<FileResponse>()
    }

    override suspend fun listFiles(): ListFilesResponse {
        return client.getRequest(
            url = "files"
        )
    }

    override suspend fun retrieveFile(fileId: String): FileResponse {
        return client.getRequest(
            url = "files/$fileId"
        )
    }

    override suspend fun deleteFile(fileId: String): FileResponse {
        return client.deleteRequest(
            url = "/files/$fileId"
        )
    }
}