package id.buaja.kmm_meal.core.network

import io.ktor.client.HttpClient
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json

@OptIn(ExperimentalSerializationApi::class)
fun setupJson() = Json {
    isLenient = true
    ignoreUnknownKeys = true
    useAlternativeNames = false
    explicitNulls = false
}

fun setupHttpClient(
    json: Json,
    baseUrl: String,
    apiVersion: String,
    isDebugMode: Boolean = true,
    httpClientEngine: MealHttpClientEngine
): HttpClient {
    return httpClientEngine.buildHttpClient().config {
        install(ContentNegotiation) {
            json(json)
        }

        defaultRequest {
            host = baseUrl

            url {
                this.user
                protocol = URLProtocol.HTTPS

                /**
                 * if baseURL = themealdb.com, then by default,
                 * the urlHttpClient will be like this: https://www.themealdb.com/api/json/v1/1/
                 */
                appendPathSegments("api/json/$apiVersion/1/")
            }
        }

        install(HttpTimeout) {
            this.requestTimeoutMillis = 60_000
            this.connectTimeoutMillis = 60_000
            this.socketTimeoutMillis = 60_000
        }

        expectSuccess = true
        HttpResponseValidator {
            handleResponseExceptionWithRequest { cause, _ ->
                throw cause
            }
        }

        if (isDebugMode) {
            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        co.touchlab.kermit.Logger.withTag("HttpClient").d {
                            "Response $message"
                        }
                    }
                }
                level = LogLevel.ALL
            }
        }
    }
}