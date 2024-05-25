package id.buaja.kmm_meal.data.repository.mapper

import id.buaja.kmm_meal.data.source.remote.response.*
import id.buaja.kmm_meal.domain.model.*

internal fun FilteredMealItemResponse.asFilteredMealDomainModel(): FilteredMeal {
    return FilteredMeal(
        idMeal = this.idMeal,
        strMeal = this.strMeal,
        strMealThumb = this.strMealThumb
    )
}

internal fun DetailMealItemResponse.asDetailMealDomainModel(): DetailMeal {
    return DetailMeal(
        idMeal = this.idMeal,
        strArea = this.strArea,
        strCategory = this.strCategory,
        strCreativeCommonsConfirmed = this.strCreativeCommonsConfirmed ?: "",
        strDrinkAlternate = this.strDrinkAlternate ?: "",
        strImageSource = this.strImageSource ?: this.strMealThumb,
        strInstructions = this.strInstructions ?: ""
    )
}