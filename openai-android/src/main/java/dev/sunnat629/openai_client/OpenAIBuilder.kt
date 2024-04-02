/**
 * @author  Mohi Us Sunnat
 * @date    01.04.24
 * Copyright ©2024 Sunnat629.dev. All rights reserved.
 */

package dev.sunnat629.openai_client

import dev.sunnat629.openai_client.models.openaAI.LoggingNetwork
import dev.sunnat629.openai_client.models.openaAI.OpenAIBuilderConfig
import dev.sunnat629.openai_client.models.openaAI.RetryNetwork

class OpenAIBuilder(token: String) {
    private var organization: String? = null
    private var refreshToken: String? = null
    private var headers: Map<String, String> = emptyMap()
    private var logging: LoggingNetwork = LoggingNetwork()
    private var retry: RetryNetwork = RetryNetwork()

    fun refreshToken(refreshToken: String) = apply { this.refreshToken = refreshToken }
    fun organization(organization: String) = apply { this.organization = organization }
    fun headers(headers: Map<String, String>) = apply { this.headers = headers }
    fun logging(logging: LoggingNetwork) = apply { this.logging = logging }
    fun retry(retry: RetryNetwork) = apply { this.retry = retry }

    private val config = OpenAIBuilderConfig(
        token = token,
        organization = organization,
        headers = headers,
        logging = logging,
        retry = retry
    )

    fun build(): OpenAI {
        return OpenAIImpl(config)
    }
}