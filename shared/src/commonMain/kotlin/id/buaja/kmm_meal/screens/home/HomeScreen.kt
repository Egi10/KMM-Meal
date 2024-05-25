package id.buaja.kmm_meal.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.*
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.*
import id.buaja.kmm_meal.core.designsystem.component.*
import id.buaja.kmm_meal.domain.model.FilteredMeal
import io.kamel.image.*
import io.ktor.http.Url

class HomeScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val homeScreenModel = getScreenModel<HomeScreenModel>()

        val state by homeScreenModel.uiState.collectAsState()

        when (val result = state) {
            is HomeState.Loading -> {
                MealLoading()
            }

            is HomeState.Success -> {
                HomeContent(
                    filteredMeals = result.filteredMeals,
                    onDetailClick = {

                    }
                )
            }

            is HomeState.Error -> {
                MealAlert(
                    title = "Ops!",
                    message = result.message,
                    buttonText = "Retry",
                    onButtonClick = {
                        homeScreenModel.getMealByArea()
                    }
                )
            }
        }

        LaunchedEffect(currentCompositeKeyHash) {
            homeScreenModel.getMealByArea()
        }
    }
}


@Composable
private fun HomeContent(
    filteredMeals: List<FilteredMeal>,
    onDetailClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(
            vertical = 8.dp
        )
    ) {
        items(
            items = filteredMeals,
            key = {
                it.idMeal
            }
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onDetailClick.invoke(it.idMeal)
                    }
                    .padding(
                        horizontal = 16.dp,
                        vertical = 8.dp
                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                KamelImage(
                    resource = asyncPainterResource(
                        Url(it.strMealThumb)
                    ),
                    contentDescription = "MealThumb",
                    modifier = Modifier
                        .size(64.dp)
                        .clip(RoundedCornerShape(8.dp))
                )

                Text(
                    text = it.strMeal,
                    fontSize = 20.sp
                )
            }
        }
    }
}