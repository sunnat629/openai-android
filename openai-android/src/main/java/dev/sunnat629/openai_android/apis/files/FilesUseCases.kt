package dev.sunnat629.openai_android.apis.files

class UploadFileUseCase(private val repository: FilesRepository) {
    suspend operator fun invoke(): MockResponse =
        repository.uploadFile()
}

class ListFilesUseCase(private val repository: FilesRepository) {
    suspend operator fun invoke(): MockResponse =
        repository.listFiles()
}

class GetFileDetailsUseCase(
    private val repository: FilesRepository,
    private val fileId: String
) {
    suspend operator fun invoke(): MockResponse =
        repository.getFileDetails(fileId)
}

class DeleteFileUseCase(
    private val repository: FilesRepository,
    private val fileId: String
) {
    suspend operator fun invoke(): MockResponse =
        repository.deleteFile(fileId)
}

class GetFileContentUseCase(
    private val repository: FilesRepository,
    private val fileId: String
) {
    suspend operator fun invoke(): MockResponse =
        repository.getFileContent(fileId)
}