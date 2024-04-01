package dev.sunnat629.openai_android.apis.images

import dev.sunnat629.openai_android.models.images.CreateImageEditRequest
import dev.sunnat629.openai_android.models.images.CreateImageRequest
import dev.sunnat629.openai_android.models.images.CreateImageResponse
import dev.sunnat629.openai_android.models.images.CreateImageVariationsRequest
import dev.sunnat629.openai_android.models.images.CreateImageVariationsResponse
import dev.sunnat629.openai_android.networks.ApiResult
import dev.sunnat629.openai_android.networks.postRequest
import io.ktor.client.HttpClient


/**
 * Interface defining operations for creating and managing images with the OpenAI API.
 */
interface ImageRepository {

    /**
     * Creates an image based on a given prompt or parameters.
     *
     * @param request The request parameters for image creation.
     * @return ApiResult<CreateImageResponse> The created image or images.
     */
    suspend fun createImage(request: CreateImageRequest): ApiResult<CreateImageResponse>

    /**
     * Edits an existing image according to the specified modifications.
     *
     * @param request The request parameters for image editing.
     * @return ApiResult<CreateImageResponse> The edited image.
     */
    suspend fun editImage(request: CreateImageEditRequest): ApiResult<CreateImageResponse>

    /**
     * Creates variations of a given image based on specified parameters.
     *
     * @param request The request parameters for creating image variations.
     * @return ApiResult<Any> The created image variations.
     */
    suspend fun createImageVariations(request: CreateImageVariationsRequest): ApiResult<CreateImageVariationsResponse>
}


class ImageRepositoryImpl(private val client: HttpClient) : ImageRepository {
    private val baseUrl = "https://api.openai.com/v1/images"

    override suspend fun createImage(request: CreateImageRequest): ApiResult<CreateImageResponse> {
        return client.postRequest(
            url = "$baseUrl/generations",
            request = request
        )
    }

    override suspend fun editImage(request: CreateImageEditRequest): ApiResult<CreateImageResponse> {
        return client.postRequest(
            url = "$baseUrl/edits",
            request = request
        )
    }

    override suspend fun createImageVariations(request: CreateImageVariationsRequest): ApiResult<CreateImageVariationsResponse> {
        return client.postRequest(
            url = "$baseUrl/variations",
            request = request
        )
    }

}