package id.buaja.kmm_meal.domain.usecase

import id.buaja.kmm_meal.domain.model.FilteredMeal
import id.buaja.kmm_meal.domain.repository.MealRepository
import id.buaja.kmm_meal.domain.utils.Result
import id.buaja.kmm_meal.domain.utils.executeWithResultFlow
import kotlinx.coroutines.flow.Flow

class GetRemoteMealByAreaUseCase(
    private val mealRepository: MealRepository
) {
    operator fun invoke(areaName: String): Flow<Result<List<FilteredMeal>>> {
        return executeWithResultFlow {
            mealRepository.getRemoteMealByArea(
                areaName = areaName
            )
        }
    }
}