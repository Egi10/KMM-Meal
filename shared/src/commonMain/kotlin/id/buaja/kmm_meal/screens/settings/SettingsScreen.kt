package id.buaja.kmm_meal.screens.settings

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen

class SettingsScreen : Screen {
    @Composable
    override fun Content() {
        SettingsContent(
            modifier = Modifier
                .fillMaxSize()
        )
    }
}

@Composable
internal fun SettingsContent(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Coming soon",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )
    }
}