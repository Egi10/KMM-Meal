package id.buaja.kmm_meal.screens.detailsmeal

import cafe.adriel.voyager.core.model.*
import id.buaja.kmm_meal.domain.usecase.GetRemoteDetailMealByIdUseCase
import id.buaja.kmm_meal.domain.utils.Result
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class DetailsMealScreenModel(
    private val getRemoteDetailMealByIdUseCase: GetRemoteDetailMealByIdUseCase
) : StateScreenModel<DetailsMealState>(DetailsMealState.Loading) {
    fun getDetailByIdeMeal(idMeal: String) {
        screenModelScope.launch {
            getRemoteDetailMealByIdUseCase.invoke(
                idMeal = idMeal
            ).collectLatest {
                when (it) {
                    Result.Loading -> {
                        mutableState.value = DetailsMealState.Loading
                    }

                    is Result.Success -> {
                        mutableState.value = DetailsMealState.Success(
                            data = it.data
                        )
                    }

                    is Result.Error -> {
                        mutableState.value = DetailsMealState.Error(
                            message = it.exception.message ?: ""
                        )
                    }
                }
            }
        }
    }
}