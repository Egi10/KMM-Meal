package id.buaja.kmm_meal.data.source.remote.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DetailMealResponse(
    @SerialName("meals")
    val meals: List<DetailMealItemResponse>
)

@Serializable
data class DetailMealItemResponse(
    @SerialName("idMeal")
    val idMeal: String,
    @SerialName("strArea")
    val strArea: String,
    @SerialName("strCategory")
    val strCategory: String,
    @SerialName("strCreativeCommonsConfirmed")
    val strCreativeCommonsConfirmed: String?,
    @SerialName("strDrinkAlternate")
    val strDrinkAlternate: String?,
    @SerialName("strImageSource")
    val strImageSource: String?,
    @SerialName("strMeal")
    val strMeal: String,
    @SerialName("strMealThumb")
    val strMealThumb: String,
    @SerialName("strInstructions")
    val strInstructions: String?
)