package id.buaja.kmm_meal.data.source.remote

import id.buaja.kmm_meal.data.remote.response.*
import id.buaja.kmm_meal.data.source.remote.response.*

interface MealRemoteDataSource {
    suspend fun getMealsByArea(areaName: String): List<FilteredMealItemResponse>
    suspend fun getDetailMealById(idMeal: String): DetailMealItemResponse?
}