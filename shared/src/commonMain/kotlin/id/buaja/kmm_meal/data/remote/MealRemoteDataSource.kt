package id.buaja.kmm_meal.data.remote

import id.buaja.kmm_meal.data.remote.response.*

interface MealRemoteDataSource {
    suspend fun getMealsByArea(areaName: String): List<FilteredMealItemResponse>
    suspend fun getDetailMealById(idMeal: String): DetailMealItemResponse?
}