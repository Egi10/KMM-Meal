package id.buaja.kmm_meal.screens.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabDisposable
import cafe.adriel.voyager.navigator.tab.TabNavigator
import id.buaja.kmm_meal.screens.home.HomeTab
import id.buaja.kmm_meal.screens.settings.SettingsTab

/**
 * Created by Julsapargi Nursam on 10/08/24
 * Mobile Engineer - Android
 */

class DashboardScreen : Screen {
    @Composable
    override fun Content() {
        TabNavigator(
            tab = HomeTab,
            tabDisposable = {
                TabDisposable(
                    navigator = it,
                    tabs = listOf(HomeTab, SettingsTab)
                )
            }
        ) {
            Scaffold(
                content = { innerPadding ->
                    Box(
                        modifier = Modifier
                            .background(
                                color = Color(
                                    color = 0XFFF6F6F6
                                )
                            )
                            .padding(innerPadding)
                    ) {
                        CurrentTab()
                    }
                },
                bottomBar = {
                    BottomNavigation(
                        backgroundColor = Color.White
                    ) {
                        TabNavigationItem(
                            tab = HomeTab
                        )
                        TabNavigationItem(
                            tab = SettingsTab
                        )
                    }
                }
            )
        }
    }
}

@Composable
private fun RowScope.TabNavigationItem(tab: Tab) {
    val tabNavigator = LocalTabNavigator.current

    BottomNavigationItem(
        selected = tabNavigator.current == tab,
        onClick = {
            tabNavigator.current = tab
        },
        icon = {
            tab.options.icon?.let { icon ->
                Icon(
                    painter = icon,
                    contentDescription =
                    tab.options.title
                )
            }
        },
        selectedContentColor = Color(
            color = 0XFFF6961E
        ),
        unselectedContentColor = Color(
            color = 0XFF2F3542
        )
    )
}