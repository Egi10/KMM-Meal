package id.buaja.kmm_meal.screens.home

import cafe.adriel.voyager.core.model.*
import id.buaja.kmm_meal.domain.usecase.GetRemoteMealByAreaUseCase
import id.buaja.kmm_meal.domain.utils.Result
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeScreenModel(
    private val getRemoteMealByAreaUseCase: GetRemoteMealByAreaUseCase
) : ScreenModel {
    private val _uiState: MutableStateFlow<HomeState> = MutableStateFlow(HomeState.Loading)
    val uiState get() = _uiState.asStateFlow()

    fun getMealByArea() {
        screenModelScope.launch {
            getRemoteMealByAreaUseCase.invoke(
                areaName = "Japanese"
            ).collect { result ->
                when (result) {
                    is Result.Loading -> {
                        _uiState.value = HomeState.Loading
                    }

                    is Result.Success -> {
                        _uiState.value = HomeState.Success(
                            filteredMeals = result.data
                        )
                    }

                    is Result.Error -> {
                        _uiState.value = HomeState.Error(
                            message = result.exception.message ?: ""
                        )
                    }
                }
            }
        }
    }
}