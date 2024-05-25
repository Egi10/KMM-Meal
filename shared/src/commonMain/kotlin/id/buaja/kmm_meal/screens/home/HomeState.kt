package id.buaja.kmm_meal.screens.home

import id.buaja.kmm_meal.domain.model.FilteredMeal

sealed class HomeState {
    data object Loading : HomeState()
    data class Success(val filteredMeals: List<FilteredMeal>) : HomeState()
    data class Error(val message: String) : HomeState()
}