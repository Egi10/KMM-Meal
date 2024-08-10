package id.buaja.kmm_meal.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.annotation.ExperimentalVoyagerApi
import cafe.adriel.voyager.core.lifecycle.LifecycleEffectOnce
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import coil3.compose.AsyncImage
import id.buaja.kmm_meal.core.designsystem.component.MealAlert
import id.buaja.kmm_meal.core.designsystem.component.MealLoading
import id.buaja.kmm_meal.domain.model.FilteredMeal
import id.buaja.kmm_meal.resources.Res
import id.buaja.kmm_meal.resources.ops
import id.buaja.kmm_meal.resources.retry
import id.buaja.kmm_meal.screens.detailsmeal.DetailsMealScreen
import id.buaja.kmm_meal.screens.utils.findRootNavigator
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

class HomeScreen : Screen {
    @OptIn(ExperimentalVoyagerApi::class)
    @Composable
    override fun Content() {
        val currentNavigator = LocalNavigator.currentOrThrow
        val targetNavigator = findRootNavigator(
            currentNavigator = currentNavigator,
            depth = 2
        )

        val viewModel = koinViewModel<HomeViewModel>()

        val state by viewModel.uiState.collectAsStateWithLifecycle()

        when (val result = state) {
            is HomeState.Loading -> {
                MealLoading()
            }

            is HomeState.Success -> {
                HomeContent(
                    modifier = Modifier
                        .fillMaxSize(),
                    filteredMeals = result.filteredMeals,
                    onDetailClick = {
                        targetNavigator.push(
                            item = DetailsMealScreen(
                                idMeal = it
                            )
                        )
                    }
                )
            }

            is HomeState.Error -> {
                MealAlert(
                    title = stringResource(
                        resource = Res.string.ops
                    ),
                    message = result.message,
                    buttonText = stringResource(
                        resource = Res.string.retry
                    ),
                    onButtonClick = {
                        viewModel.getMealByArea()
                    }
                )
            }
        }

        LifecycleEffectOnce {
            viewModel.getMealByArea()
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
                AsyncImage(
                    modifier = Modifier
                        .size(64.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    model = it.strMealThumb,
                    contentDescription = it.strMeal
                )

                Text(
                    text = it.strMeal,
                    fontSize = 20.sp
                )
            }
        }
    }
}