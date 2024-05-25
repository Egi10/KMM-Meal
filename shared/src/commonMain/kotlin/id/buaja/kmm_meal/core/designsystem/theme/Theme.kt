package id.buaja.kmm_meal.core.designsystem.theme

import androidx.compose.material.*
import androidx.compose.runtime.Composable

@Composable
fun MealTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = lightColors(),
        content = content
    )
}