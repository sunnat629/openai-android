package dev.sunnat629.openai_android.files

data class MockResponse(val result: String)

interface FilesRepository {

    // Files Operations
    suspend fun uploadFile(): MockResponse
    suspend fun listFiles(): MockResponse
    suspend fun getFileDetails(fileId: String): MockResponse
    suspend fun deleteFile(fileId: String): MockResponse
    suspend fun getFileContent(fileId: String): MockResponse
}