package id.buaja.kmm_meal.screens.detailsmeal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.buaja.kmm_meal.domain.usecase.GetRemoteDetailMealByIdUseCase
import id.buaja.kmm_meal.domain.utils.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class DetailsMealViewModel(
    private val getRemoteDetailMealByIdUseCase: GetRemoteDetailMealByIdUseCase
) : ViewModel() {
    private val _uiState: MutableStateFlow<DetailsMealState> = MutableStateFlow(DetailsMealState.Loading)
    val uiState get() = _uiState.asStateFlow()

    fun getDetailByIdeMeal(idMeal: String) {
        viewModelScope.launch {
            getRemoteDetailMealByIdUseCase.invoke(
                idMeal = idMeal
            ).collectLatest {
                when (it) {
                    Result.Loading -> {
                        _uiState.value = DetailsMealState.Loading
                    }

                    is Result.Success -> {
                        _uiState.value = DetailsMealState.Success(
                            data = it.data
                        )
                    }

                    is Result.Error -> {
                        _uiState.value = DetailsMealState.Error(
                            message = it.exception.message ?: ""
                        )
                    }
                }
            }
        }
    }
}