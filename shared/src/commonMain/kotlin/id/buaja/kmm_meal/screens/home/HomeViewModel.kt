package id.buaja.kmm_meal.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.buaja.kmm_meal.domain.usecase.GetRemoteMealByAreaUseCase
import id.buaja.kmm_meal.domain.utils.Result
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getRemoteMealByAreaUseCase: GetRemoteMealByAreaUseCase
) : ViewModel() {
    private val _uiState: MutableStateFlow<HomeState> = MutableStateFlow(HomeState.Loading)
    val uiState get() = _uiState.asStateFlow()

    fun getMealByArea() {
        viewModelScope.launch {
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