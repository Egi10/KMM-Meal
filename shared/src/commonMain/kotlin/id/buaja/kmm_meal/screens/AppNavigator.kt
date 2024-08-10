package id.buaja.kmm_meal.screens

import androidx.compose.runtime.Composable
import id.buaja.kmm_meal.screens.splashscreen.SplashScreen
import id.buaja.kmm_meal.screens.utils.MealNavigator

@Composable
internal fun AppNavigator() {
    MealNavigator(
        screen = SplashScreen()
    )
}
 