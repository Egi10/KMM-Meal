package id.buaja.kmm_meal.core.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.darwin.Darwin

class IOSMealHttpClientEngine : MealHttpClientEngine {
    override fun buildHttpClient(): HttpClient {
        return HttpClient(Darwin) {
            engine {
                configureRequest {
                    setAllowsCellularAccess(true)
                }
            }
        }
    }
}