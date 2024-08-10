package id.buaja.kmm_meal.screens.utils

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.annotation.ExperimentalVoyagerApi
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.jetpack.ProvideNavigatorLifecycleKMPSupport
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.NavigatorDisposeBehavior
import cafe.adriel.voyager.navigator.OnBackPressed
import cafe.adriel.voyager.transitions.SlideTransition

@OptIn(ExperimentalVoyagerApi::class)
@Composable
internal fun MealNavigator(
    screen: Screen,
    onBackPressed: OnBackPressed = { true }
) {
    ProvideNavigatorLifecycleKMPSupport {
        Navigator(
            screen = screen,
            disposeBehavior = NavigatorDisposeBehavior(disposeSteps = true),
            onBackPressed = onBackPressed
        ) { navigator ->
            SlideTransition(
                navigator = navigator,
                disposeScreenAfterTransitionEnd = true
            )
        }
    }
}

fun findRootNavigator(
    currentNavigator: Navigator,
    depth: Int
): Navigator {
    /**
     * Recursively finds the root navigator in a navigation hierarchy.
     *
     * @param currentNavigator The current navigator being examined.
     * @param depth The maximum depth of the search.
     * @return The root navigator of the hierarchy.
     *
     * This function traverses the navigation hierarchy upwards until it reaches the root
     * navigator, which has no parent. The `depth` parameter is used to limit the search
     * to a specific depth, preventing infinite recursion in case of circular references.
     */
    return if (depth == 0 || currentNavigator.parent == null) {
        // Base case:
        // - If the depth is 0, the maximum search depth has been reached.
        // - If the current navigator has no parent, it is the root.
        currentNavigator
    } else {
        // Recursive case:
        // Continue searching in the parent of the current navigator.
        return findRootNavigator(
            currentNavigator = currentNavigator.parent!!,
            depth = depth - 1
        )
    }
}