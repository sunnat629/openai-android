/**
 * @author  Mohi Us Sunnat
 * @date    01.04.24
 * Copyright ©2024 Sunnat629.dev. All rights reserved.
 */

package dev.sunnat629.openai_android

import dev.sunnat629.openai_android.models.openaAI.LoggingNetwork
import dev.sunnat629.openai_android.models.openaAI.OpenAIBuilderConfig
import dev.sunnat629.openai_android.models.openaAI.RetryNetwork

class OpenAIBuilder(apiKey: String) {
    private var organization: String? = null
    private var refreshToken: String? = null
    private var headers: Map<String, String> = emptyMap()
    private var logging: LoggingNetwork = LoggingNetwork()
    private var retry: RetryNetwork = RetryNetwork()

    fun setRefreshToken(refreshToken: String) = apply { this.refreshToken = refreshToken }
    fun setOrganization(organization: String) = apply { this.organization = organization }
    fun setHeaders(headers: Map<String, String>) = apply { this.headers = headers }
    fun setLogging(logging: LoggingNetwork) = apply { this.logging = logging }
    fun setRetry(retry: RetryNetwork) = apply { this.retry = retry }

    private val config = OpenAIBuilderConfig(
        apiKey = apiKey,
        organization = organization,
        headers = headers,
        logging = logging,
        retry = retry
    )

    fun build(): OpenAI { return OpenAIImpl(config) }
}