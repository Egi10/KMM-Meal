package id.buaja.kmm_meal.domain.repository

import id.buaja.kmm_meal.domain.model.*

interface MealRepository {
    suspend fun getRemoteMealByArea(areaName: String): List<FilteredMeal>
    suspend fun getRemoteDetailMealById(idMeal: String): DetailMeal?
}