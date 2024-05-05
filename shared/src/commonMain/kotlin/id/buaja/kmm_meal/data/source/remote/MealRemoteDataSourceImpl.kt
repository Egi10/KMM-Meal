package id.buaja.kmm_meal.data.source.remote

import id.buaja.kmm_meal.data.source.remote.response.*
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.parameters
import kotlinx.coroutines.*

class MealRemoteDataSourceImpl(
    private val httpClient: HttpClient,
    private val dispatcher: CoroutineDispatcher
) : MealRemoteDataSource {
    override suspend fun getMealsByArea(areaName: String): List<FilteredMealItemResponse> =
        withContext(dispatcher) {
            val response = httpClient.get("filter.php") {
                url {
                    parameters {
                        append("a", areaName)
                    }
                }
            }.body<FilteredMealResponse>()

            return@withContext response.meals
        }

    override suspend fun getDetailMealById(idMeal: String): DetailMealItemResponse? =
        withContext(dispatcher) {
            val response = httpClient.get("lookup.php") {
                url {
                    parameters {
                        append("i", idMeal)
                    }
                }
            }.body<DetailMealResponse>()

            return@withContext response.meals.firstOrNull()
        }
}