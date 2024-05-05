package id.buaja.kmm_meal.core.network

import io.ktor.client.HttpClient

interface MealHttpClientEngine {
    fun buildHttpClient(): HttpClient
}