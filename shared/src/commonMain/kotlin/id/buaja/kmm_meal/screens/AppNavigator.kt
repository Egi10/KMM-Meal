package id.buaja.kmm_meal.screens

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.annotation.ExperimentalVoyagerApi
import cafe.adriel.voyager.jetpack.ProvideNavigatorLifecycleKMPSupport
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.NavigatorDisposeBehavior
import cafe.adriel.voyager.transitions.SlideTransition
import id.buaja.kmm_meal.screens.home.HomeScreen

@OptIn(ExperimentalVoyagerApi::class)
@Composable
internal fun AppNavigator() {
    ProvideNavigatorLifecycleKMPSupport {
        Navigator(
            screen = HomeScreen(),
            disposeBehavior = NavigatorDisposeBehavior(disposeSteps = true)
        ) { navigator ->
            SlideTransition(
                navigator = navigator,
                disposeScreenAfterTransitionEnd = true
            )
        }
    }
}
 