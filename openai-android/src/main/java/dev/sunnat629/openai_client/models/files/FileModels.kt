/**
 * @author  Mohi Us Sunnat
 * @date    01.04.24
 * Copyright ©2024 Sunnat629.dev. All rights reserved.
 */

package dev.sunnat629.openai_client.models.files

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UploadFileRequest(
    @SerialName("file") val file: String, // Placeholder for file data
    @SerialName("purpose") val purpose: String
)

@Serializable
data class FileResponse(
    @SerialName("id") val id: String,
    @SerialName("object") val objectContent: String,
    @SerialName("created_at") val createdAt: Long,
    @SerialName("filename") val filename: String,
    @SerialName("purpose") val purpose: String
)

@Serializable
data class ListFilesResponse(
    @SerialName("data") val data: List<FileResponse>
)