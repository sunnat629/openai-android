/**
 * @author  Mohi Us Sunnat
 * @date    01.04.24
 * Copyright ©2024 Sunnat629.dev. All rights reserved.
 */

package dev.sunnat629.openai_client.models.images

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateImageRequest(
    @SerialName("prompt") val prompt: String,
    @SerialName("model") val model: String? = null, // Optional; defaults to "dall-e-2" if not specified
    @SerialName("n") val n: Int? = null, // Optional; defaults to 1
    @SerialName("quality") val quality: String? = null, // Optional; "standard" or "hd", specific to dall-e-3
    @SerialName("responseFormat") val responseFormat: String? = null, // Optional; "url" or "b64_json"
    @SerialName("size") val size: String? = null, // Optional; size of generated images (e.g., "1024x1024")
    @SerialName("style") val style: String? = null, // Optional; "vivid" or "natural", specific to dall-e-3
    @SerialName("user") val user: String? = null // Optional; unique identifier for the end-user
)

@Serializable
data class CreateImageResponse(
    @SerialName("created") val created: Long,
    @SerialName("data") val data: List<ImageData>
)

@Serializable
data class ImageData(
    @SerialName("url") val url: String // or the base64 encoded string depending on the request's response_format
)

@Serializable
data class CreateImageEditRequest(
    @SerialName("prompt") val prompt: String,
    @SerialName("model") val model: String? = null, // Only "dall-e-2" is supported currently
    @SerialName("n") val n: Int? = null, // Number of images to generate
    @SerialName("size") val size: String? = null // Size of the generated images
    // Note: The actual image and mask files would be sent as multipart file uploads, not directly in the data class
)

@Serializable
data class CreateImageVariationsRequest(
    @SerialName("image") val image: ByteArray, // The original image, sent as a multipart file upload
    @SerialName("model") val model: String? = null, // Optional; specifies the model to use
    @SerialName("n") val n: Int? = null, // Optional; the number of variations to generate
    @SerialName("responseFormat") val responseFormat: String? = null // Optional; "url" or "b64_json"
)

@Serializable
data class CreateImageVariationsResponse(
    @SerialName("created") val created: Long,
    @SerialName("data") val data: List<ImageData> // Reusing the ImageData class from CreateImageResponse
)