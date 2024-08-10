package id.buaja.kmm_meal.screens.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import id.buaja.kmm_meal.resources.Res
import id.buaja.kmm_meal.resources.retry
import id.buaja.kmm_meal.screens.utils.MealNavigator
import org.jetbrains.compose.resources.stringResource

object HomeTab : Tab {
    @Composable
    override fun Content() {
        MealNavigator(
            screen = HomeScreen()
        )
    }

    override val options: TabOptions
        @Composable
        get() {
            val title = stringResource(Res.string.retry)
            val icon = rememberVectorPainter(Icons.Default.Home)

            return remember {
                TabOptions(
                    index = 0u,
                    title = title,
                    icon = icon
                )
            }
        }
}