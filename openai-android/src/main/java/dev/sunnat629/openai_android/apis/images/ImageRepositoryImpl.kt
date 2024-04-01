/**
 * @author  Mohi Us Sunnat
 * @date    01.04.24
 * Copyright ©2024 Sunnat629.dev. All rights reserved.
 */

package dev.sunnat629.openai_android.apis.images

import dev.sunnat629.openai_android.models.images.CreateImageEditRequest
import dev.sunnat629.openai_android.models.images.CreateImageRequest
import dev.sunnat629.openai_android.models.images.CreateImageResponse
import dev.sunnat629.openai_android.models.images.CreateImageVariationsRequest
import dev.sunnat629.openai_android.models.images.CreateImageVariationsResponse
import dev.sunnat629.openai_android.networks.ApiResult
import dev.sunnat629.openai_android.networks.postRequest
import io.ktor.client.HttpClient

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