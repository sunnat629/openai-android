/** * @author  Mohi Us Sunnat * @date    01.04.24 * Copyright ©2024 Sunnat629.dev. All rights reserved. */package dev.sunnat629.openai_client.networksimport dev.sunnat629.openai_client.models.openaAI.OpenAIBuilderConfigimport dev.sunnat629.openai_client.networks.URLs.baseUrlimport io.ktor.client.HttpClientimport io.ktor.client.engine.okhttp.OkHttpimport io.ktor.client.plugins.DefaultRequestimport io.ktor.client.plugins.HttpRequestRetryimport io.ktor.client.plugins.HttpTimeoutimport io.ktor.client.plugins.auth.Authimport io.ktor.client.plugins.auth.providers.BearerTokensimport io.ktor.client.plugins.auth.providers.bearerimport io.ktor.client.plugins.contentnegotiation.ContentNegotiationimport io.ktor.client.plugins.logging.DEFAULTimport io.ktor.client.plugins.logging.LogLevelimport io.ktor.client.plugins.logging.Loggerimport io.ktor.client.plugins.logging.Loggingimport io.ktor.http.ContentTypeimport io.ktor.http.contentTypeimport io.ktor.serialization.kotlinx.KotlinxSerializationConverterimport io.ktor.util.appendIfNameAbsentimport kotlinx.serialization.json.Jsonimport okhttp3.logging.HttpLoggingInterceptorfun ktorHttpClient(config: OpenAIBuilderConfig): HttpClient {    return HttpClient(OkHttp) {        engine {            // Configure OkHttp specific settings            addInterceptor(HttpLoggingInterceptor().apply {                level = HttpLoggingInterceptor.Level.BODY            })            config {                // this: OkHttpClient.Builder                followRedirects(true)                // ...            }        }        install(Auth) {            bearer {                loadTokens {                    // Load tokens from a local storage and return them as the 'BearerTokens' instance                    BearerTokens(config.token, config.refreshToken)                }                refreshTokens { // this: RefreshTokensParams                    // Refresh tokens and return them as the 'BearerTokens' instance                    BearerTokens(config.token, config.refreshToken)                }            }        }        // Install JSON serialization plugin        install(ContentNegotiation) {            register(ContentType.Application.Json, KotlinxSerializationConverter(Json {                // Configuration options for kotlinx.serialization                isLenient = true                ignoreUnknownKeys = true            }))        }        install(DefaultRequest) {            url(baseUrl)            config.organization?.let { organization ->                headers.append(                    "OpenAI-Organization",                    organization                )            }            config.headers.onEach { (key, value) -> headers.appendIfNameAbsent(key, value) }            contentType(ContentType.Application.Json)        }        install(HttpTimeout) {            config.timeout.socketTimeout?.let { socketTimeout ->                socketTimeoutMillis = socketTimeout            }            config.timeout.connectTimeout?.let { connectTimeout ->                connectTimeoutMillis = connectTimeout            }            config.timeout.requestTimeout?.let { requestTimeout ->                requestTimeoutMillis = requestTimeout            }        }        install(HttpRequestRetry) {            maxRetries = config.retry.maxAttempts            // retry on rate limit error.            retryIf { _, response -> response.status.value.let { it == 429 } }            exponentialDelay(config.retry.base, config.retry.delay)        }        // Install Logging plugin        install(Logging) {            logger = Logger.DEFAULT            level = LogLevel.ALL        }        // Default request configuration        // Setup DefaultRequest to add headers to every request    }}