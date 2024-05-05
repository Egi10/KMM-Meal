package id.buaja.kmm_meal.core.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp

class AndroidMealHttpClientEngine : MealHttpClientEngine {
    override fun buildHttpClient(): HttpClient {
        return HttpClient(OkHttp) {
            engine {
                config {
                    followRedirects(true)
                }
            }
        }
    }
}