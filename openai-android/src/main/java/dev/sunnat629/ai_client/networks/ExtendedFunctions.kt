/**
 * @author  Mohi Us Sunnat
 * @date    01.04.24
 * Copyright ©2024 Sunnat629.dev. All rights reserved.
 */

package dev.sunnat629.ai_client.networks

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.request
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

// Assuming you have an HttpClient instance defined as `client`

suspend inline fun <reified T> HttpClient.getRequest(
    url: String,
    contentType: ContentType = ContentType.Application.Json,
): T = makeRequest(HttpMethod.Get, url = url, contentType = contentType)

suspend inline fun <reified T> HttpClient.patchRequest(
    url: String,
    request: Any,
    contentType: ContentType = ContentType.Application.Json,
): T = makeRequest(HttpMethod.Post, url, body = request, contentType = contentType)

suspend inline fun <reified T> HttpClient.postRequest(
    url: String,
    request: Any,
    contentType: ContentType = ContentType.Application.Json,
): T = makeRequest(HttpMethod.Post, url, body = request, contentType = contentType)

suspend inline fun <reified T> HttpClient.putRequest(
    url: String,
    request: Any,
    contentType: ContentType = ContentType.Application.Json,
): T = makeRequest(HttpMethod.Put, url, body = request, contentType = contentType)

suspend inline fun <reified T> HttpClient.deleteRequest(
    url: String,
    request: Any? = null,
    contentType: ContentType = ContentType.Application.Json,
): T = makeRequest(HttpMethod.Delete, url, body = request, contentType = contentType)

suspend inline fun <reified T> HttpClient.makeRequest(
    method: HttpMethod,
    url: String,
    contentType: ContentType,
    body: Any? = null // Optional, used for POST, PUT, DELETE
): T = withContext(Dispatchers.IO) {
    val response: HttpResponse = request(url) {
        this.method = method
        contentType(contentType)
        if (body != null) {
            this.setBody(body)
        }
    }

    if (response.status.isSuccess()) {
        response.body()
    } else {
        throw RuntimeException("Received non-success status: ${response.status}")
    }
}
