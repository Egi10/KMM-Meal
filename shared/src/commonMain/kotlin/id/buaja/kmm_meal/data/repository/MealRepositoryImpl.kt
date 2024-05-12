package id.buaja.kmm_meal.data.repository

import id.buaja.kmm_meal.data.repository.mapper.*
import id.buaja.kmm_meal.data.repository.mapper.asFilteredMealDomainModel
import id.buaja.kmm_meal.data.source.remote.MealRemoteDataSource
import id.buaja.kmm_meal.domain.model.*
import id.buaja.kmm_meal.domain.repository.MealRepository

class MealRepositoryImpl(
    private val mealRemoteDataSource: MealRemoteDataSource
) : MealRepository {
    override suspend fun getRemoteMealByArea(areaName: String): List<FilteredMeal> {
        return mealRemoteDataSource.getMealsByArea(
            areaName = areaName
        ).map {
            return@map it.asFilteredMealDomainModel()
        }
    }

    override suspend fun getRemoteDetailMealById(idMeal: String): DetailMeal? {
        return mealRemoteDataSource.getDetailMealById(
            idMeal = idMeal
        )?.asDetailMealDomainModel()
    }
}