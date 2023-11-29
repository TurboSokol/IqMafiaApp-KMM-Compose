package com.turbosokol.iqmafiaapp.service

import com.turbosokol.iqmafiaapp.data.core.ApiResponse
import com.turbosokol.iqmafiaapp.data.core.ErrorResponse
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.http.HeadersBuilder
import io.ktor.http.HttpStatusCode
import io.ktor.http.authority
import io.ktor.http.path
import kotlinx.serialization.ExperimentalSerializationApi
import kotlin.time.ExperimentalTime

/***
 *If this code runs it was created by Evgenii Sokol.
 *If it doesn’t work, I don’t know who created it.
 ***/

const val BASE_URL = "https://iq.vd-create.ru"
const val BEARER = "S(^&sad*%ASD7as6d5%AS^(%D"

class KtorWebService(
    val logService: LogService
) {

    val client = getClient(logService)

    suspend inline fun <reified T : Any> makeJsonGet(
        endpoint: String,
        retry: Boolean = false,
        baseUrl: String = BASE_URL
    ): ApiResponse<T> {
        val url = baseUrl + endpoint
        var retryCount = if (retry) 1 else 0
        while (retryCount >= 0) {
            try {
                val data = client.get {
                    url {
                        host = url
                        path(endpoint)
                    }

                    bearerAuth(BEARER)
                }

                logService.logTrace("GET '$url' SUCCESS")
                return ApiResponse(true, data as T, null)
//            } catch (e: WebClientException) {
//                retryCount--
//                if (retryCount < 0)
//                    return ApiResponse(false, null, ErrorResponse("", e.statusCode.value))
//            } catch (e: NoTransformationFoundException) {
//                retryCount--
//                if (retryCount < 0)
//                    return ApiResponse(true, null, null)
            } catch (e: Exception) {
                retryCount--
                logService.logError("!!! GET '$url' FAILED: '${e.toString()}'")
            }
        }
        return ApiResponse(false, null, ErrorResponse("", 0))
    }

    suspend inline fun <reified TResponse : Any, reified TRequest : Any> makeJsonPost(
        endpoint: String,
        request: TRequest,
        authorizationHeader: String,
        retry: Boolean = false,
        baseUrl: String = BASE_URL,
        crossinline customHeaders: HeadersBuilder.() -> Unit = {}
    ): ApiResponse<TResponse> {
        val url = baseUrl + endpoint

        var retryCount = if (retry) 1 else 0
        while (retryCount >= 0) {
            try {
                val data = client.post(url) {

                }

                logService.logTrace("POST '$url' SUCCESS")
                return ApiResponse(true, data as TResponse, null)
            } catch (e: WebClientException) {
                retryCount--
                if (retryCount < 0)
                    return ApiResponse(false, null, ErrorResponse("", e.statusCode.value))
            } catch (e: NoTransformationFoundException) {
                retryCount--
                if (retryCount < 0)
                    return ApiResponse(true, null, null)
            } catch (e: Exception) {
                retryCount--
                logService.logError("!!! POST '$url' FAILED: '${e.message}'")
            }
        }
        return ApiResponse(false, null, ErrorResponse("", 0))
    }

    suspend inline fun <reified TResponse : Any, reified TRequest : Any> makeJsonPut(
        endpoint: String,
        request: TRequest,
        authorizationHeader: String,
        baseUrl: String = BASE_URL,
        crossinline customHeaders: HeadersBuilder.() -> Unit = {}
    ): ApiResponse<TResponse> {
        val url = baseUrl + endpoint
        try {
            val data = client.put(url)

            logService.logTrace("PUT '$url' SUCCESS")
            return ApiResponse(true, data as TResponse, null)
        } catch (e: WebClientException) {
            return ApiResponse(false, null, ErrorResponse("", e.statusCode.value))
        } catch (e: NoTransformationFoundException) {
            return ApiResponse(true, null, null)
        } catch (e: Exception) {
            logService.logError("!!! PUT '$url' FAILED: '${e.message}'")
        }
        return ApiResponse(false, null, ErrorResponse("", 0))
    }

    @OptIn(ExperimentalSerializationApi::class, ExperimentalTime::class)
    @PublishedApi
    internal fun getClient(
        logService: LogService
    ): HttpClient {
        return httpClient() {
            install(ContentNegotiation) {
                kotlinx.serialization.json.Json { ignoreUnknownKeys = true }
            }
//            install(Auth) {
//                bearer {
//                    loadTokens {
//                        BearerTokens(BEARER, "")
//                    }
//                }
//            }
            install(Logging) {
                level = LogLevel.ALL
                logger = WebClientLogger(logService)
            }
        }
    }
}

class WebClientException(val statusCode: HttpStatusCode) : Exception()

expect fun httpClient(config: HttpClientConfig<*>.() -> Unit): HttpClient
