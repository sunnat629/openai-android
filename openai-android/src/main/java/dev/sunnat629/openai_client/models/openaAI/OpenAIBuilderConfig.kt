/**
 * @author  Mohi Us Sunnat
 * @date    01.04.24
 * Copyright ©2024 Sunnat629.dev. All rights reserved.
 */

package dev.sunnat629.openai_client.models.openaAI

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents the configuration model for building an OpenAI API client.
 *
 * @property token The API key used for authenticating requests to the OpenAI API.
 * @property organization An optional organization identifier for the API key.
 * @property headers A map of custom headers to include in every request to the API.
 * @property logging Configuration for logging network requests and responses.
 * @property retry Configuration for retrying failed network requests.
 * @property timeout Configuration for network timeouts.
 */
@Serializable
data class OpenAIBuilderConfig(
    @SerialName("api_key") val token: String,
    @SerialName("refresh_token") val refreshToken: String = "",
    @SerialName("organization") var organization: String? = null,
    @SerialName("headers") var headers: Map<String, String> = emptyMap(),
    @SerialName("logging") var logging: LoggingNetwork = LoggingNetwork(),
    @SerialName("retry") var retry: RetryNetwork = RetryNetwork(),
    @SerialName("timeout") var timeout: TimeoutNetwork = TimeoutNetwork(),
)

/**
 * Configuration options for logging network requests and responses.
 *
 * @property enabled Flag indicating whether logging is enabled.
 * @property level The level of logging detail.
 */
@Serializable
data class LoggingNetwork(
    // Flag to enable or disable logging of network requests.
    @SerialName("enabled") val enabled: Boolean = false,

    // The level of logging detail (e.g., BASIC, HEADERS, BODY).
    @SerialName("level") val level: String = "BASIC"
)

/**
 * Configuration options for retrying failed network requests.
 *
 * @property enabled Flag indicating whether retry is enabled.
 * @property maxAttempts The maximum number of retry attempts.
 * @property delay The delay between retry attempts.
 * @param base retry base value
 */
@Serializable
data class RetryNetwork(
    // Flag to enable or disable retrying of network requests.
    @SerialName("enabled") val enabled: Boolean = false,

    // Maximum number of attempts to retry a failed network request.
    @SerialName("max_attempts") val maxAttempts: Int = 3,

    // Delay between retry attempts, in milliseconds.
    @SerialName("delay") val delay: Long = 1000,

    // base retry base value
    @SerialName("base") val base: Double = 2.0
)

/**
 * Configuration options for network timeouts.
 *
 * @property requestTimeout The maximum time to wait for processing an HTTP call from sending a request to receiving a response.
 * @property connectTimeout The maximum time to wait for establishing a connection with the server.
 * @property socketTimeout The maximum time of inactivity between two data packets when exchanging data with a server.
 *
 * All timeout values are in milliseconds. A value of `null` means the timeout is not specified.
 */
@Serializable
data class TimeoutNetwork(
    // Maximum time in milliseconds to process an HTTP call.
    @SerialName("request_timeout") val requestTimeout: Long? = null,

    // Time in milliseconds to wait for establishing a connection.
    @SerialName("connect_timeout") val connectTimeout: Long? = null,

    // Maximum time of inactivity in milliseconds between two data packets.
    @SerialName("socket_timeout") val socketTimeout: Long? = null
)