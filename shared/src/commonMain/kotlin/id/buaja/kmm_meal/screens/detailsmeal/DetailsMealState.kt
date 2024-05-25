package id.buaja.kmm_meal.screens.detailsmeal

import id.buaja.kmm_meal.domain.model.DetailMeal

sealed class DetailsMealState {
    data object Loading : DetailsMealState()
    data class Success(val data: DetailMeal) : DetailsMealState()
    data class Error(val message: String) : DetailsMealState()
}