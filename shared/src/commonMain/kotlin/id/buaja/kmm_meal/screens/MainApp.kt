package id.buaja.kmm_meal.screens

import androidx.compose.runtime.Composable
import id.buaja.kmm_meal.core.designsystem.theme.MealTheme

@Composable
fun MainApp() {
    MealTheme {
        AppNavigator()
    }
}