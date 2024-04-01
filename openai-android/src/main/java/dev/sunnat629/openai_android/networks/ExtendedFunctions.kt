package dev.sunnat629.openai_android.networks

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.request
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

// Assuming you have an HttpClient instance defined as `client`

suspend inline fun <reified T> HttpClient.getRequest(
    url: String
): ApiResult<T> = makeRequest(HttpMethod.Get, url)

suspend inline fun <reified T> HttpClient.patchRequest(
    url: String,
    request: Any
): ApiResult<T> = makeRequest(HttpMethod.Post, url, body = request)

suspend inline fun <reified T> HttpClient.postRequest(
    url: String,
    request: Any
): ApiResult<T> = makeRequest(HttpMethod.Post, url, body = request)

suspend inline fun <reified T> HttpClient.putRequest(
    url: String,
    request: Any
): ApiResult<T> = makeRequest(HttpMethod.Put, url, body = request)

suspend inline fun <reified T> HttpClient.deleteRequest(
    url: String,
    request: Any? = null
): ApiResult<T> = makeRequest(HttpMethod.Delete, url, body = request)

suspend inline fun <reified T> HttpClient.makeRequest(
    method: HttpMethod,
    url: String,
    contentType: ContentType = ContentType.Application.Json,
    body: Any? = null // Optional, only used for POST, PUT, DELETE
): ApiResult<T> = try {
    val response = withContext(Dispatchers.IO) {
        request(url) {
            this.method = method
            if (body != null) {
                this.contentType(contentType)
                this.setBody(body)
            }
        }
    }

    if (response.status.isSuccess()) {
        ApiResult.Success(response.body<T>())
    } else {
        ApiResult.Failure(RuntimeException("Received non-success status: ${response.status}"))
    }
} catch (e: Exception) {
    ApiResult.Failure(e)
}

