package id.buaja.kmm_meal.screens.splashscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import id.buaja.kmm_meal.resources.Res
import id.buaja.kmm_meal.screens.dashboard.DashboardScreen
import io.github.alexzhirkevich.compottie.LottieCompositionSpec
import io.github.alexzhirkevich.compottie.animateLottieCompositionAsState
import io.github.alexzhirkevich.compottie.rememberLottieComposition
import io.github.alexzhirkevich.compottie.rememberLottiePainter
import org.jetbrains.compose.resources.ExperimentalResourceApi

class SplashScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        SplashContent(
            modifier = Modifier
                .fillMaxSize(),
            onFinishLottie = {
                navigator.push(
                    item = DashboardScreen()
                )
            }
        )
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
internal fun SplashContent(
    modifier: Modifier,
    onFinishLottie: () -> Unit
) {
    val composition by rememberLottieComposition {
        LottieCompositionSpec.JsonString(
            Res.readBytes("files/splash-screen.json").decodeToString()
        )
    }
    val progress by animateLottieCompositionAsState(composition)

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = rememberLottiePainter(
                composition = composition,
                progress = { progress },
            ),
            contentDescription = "Lottie animation"
        )
    }

    // Animation completes.
    if (progress == 1.0f) {
        onFinishLottie.invoke()
    }
}