/**
 * @author  Mohi Us Sunnat
 * @date    01.04.24
 * Copyright ©2024 Sunnat629.dev. All rights reserved.
 */

package dev.sunnat629.ai_client.apis.images

import dev.sunnat629.ai_client.models.images.CreateImageEditRequest
import dev.sunnat629.ai_client.models.images.CreateImageRequest
import dev.sunnat629.ai_client.models.images.CreateImageResponse
import dev.sunnat629.ai_client.models.images.CreateImageVariationsRequest
import dev.sunnat629.ai_client.models.images.CreateImageVariationsResponse
import dev.sunnat629.ai_client.networks.ApiResult

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