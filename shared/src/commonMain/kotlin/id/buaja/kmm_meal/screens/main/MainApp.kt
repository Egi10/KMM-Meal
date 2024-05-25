package id.buaja.kmm_meal.screens.main

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import id.buaja.kmm_meal.core.designsystem.theme.MealTheme
import id.buaja.kmm_meal.screens.home.HomeScreen

@Composable
fun MainApp() {
    MealTheme {
        Navigator(HomeScreen())
    }
}