package id.buaja.kmm_meal.domain.usecase

import id.buaja.kmm_meal.domain.model.DetailMeal
import id.buaja.kmm_meal.domain.repository.MealRepository
import id.buaja.kmm_meal.domain.utils.*
import kotlinx.coroutines.flow.*

class GetRemoteDetailMealByIdUseCase(
    private val mealRepository: MealRepository
) {
    operator fun invoke(idMeal: String): Flow<Result<DetailMeal>> {
        return executeWithFlow {
            mealRepository.getRemoteDetailMealById(
                idMeal = idMeal
            )
        }.map {
            return@map it?.let {
                Result.Success(it)
            } ?: kotlin.run {
                Result.Error(Exception("no found"))
            }
        }.catch {
            emit(Result.Error(it))
        }.onStart {
            emit(Result.Loading)
        }
    }
}