package id.buaja.kmm_meal.data.remote.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FilteredMealResponse(
    @SerialName("meals")
    val meals: List<FilteredMealItemResponse>
)

@Serializable
data class FilteredMealItemResponse(
    @SerialName("idMeal")
    val idMeal: String,
    @SerialName("strMeal")
    val strMeal: String,
    @SerialName("strMealThumb")
    val strMealThumb: String
)