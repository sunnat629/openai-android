/**
 * @author  Mohi Us Sunnat
 * @date    07.04.24
 * Copyright ©2024 Sunnat629.dev. All rights reserved.
 */

package dev.sunnat629.ai_client.clients

import android.content.Context
import android.net.Uri
import dev.sunnat629.ai_client.apis.files.FilesRepository
import dev.sunnat629.ai_client.di.JsonUtils
import dev.sunnat629.ai_client.models.files.FileResponse
import dev.sunnat629.ai_client.models.files.ListFilesResponse
import dev.sunnat629.ai_client.models.files.Purpose
import dev.sunnat629.ai_client.models.files.UploadFileRequest
import dev.sunnat629.ai_client.utils.uriToByteArray
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface File {

    fun file(context: Context, file: Uri): File
    fun file(context: Context, jsonlFile: String): File
    fun purpose(purpose: Purpose): File
    suspend fun uploadFile(): Flow<FileResponse>
    suspend fun listFiles(): Flow<ListFilesResponse>
    suspend fun retrieveFile(fileId: String): Flow<FileResponse>
    suspend fun deleteFile(fileId: String): Flow<FileResponse>
}

class FileImpl(private val repository: FilesRepository): File {

    private var _context: Context? = null
    private var _file: Uri? = null
    private var _jsonlFile: String? = null
    private var _purpose: Purpose? = null

    override fun file(context: Context, file: Uri): File {
        this._context = context
        this._file = file
        return this
    }

    override fun file(context: Context, jsonlFile: String): File {
        this._context = context
        this._jsonlFile = jsonlFile
        return this
    }

    override fun purpose(purpose: Purpose): File {
        this._purpose = purpose
        return this
    }

    override suspend fun uploadFile(): Flow<FileResponse> {
        return flow {
            if ((_file == null ||_jsonlFile == null) && _purpose == null) throw NullPointerException("Content is null")
            val fileJsonl = JsonUtils.convertTxtToJsonl(_context, _file!!) ?: throw IllegalArgumentException("Content is null")
            val byteArray = uriToByteArray(_context, _file!!)


            val request = UploadFileRequest(purpose = _purpose!!, fileJsonl = fileJsonl)
            emit(repository.uploadFile(request, byteArray))
        }
    }

    override suspend fun listFiles(): Flow<ListFilesResponse> {
        return flow { emit(repository.listFiles()) }
    }

    override suspend fun retrieveFile(fileId: String): Flow<FileResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteFile(fileId: String): Flow<FileResponse> {
        TODO("Not yet implemented")
    }

}