package id.buaja.kmm_meal.screens.detailsmeal

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.*
import id.buaja.kmm_meal.core.designsystem.component.*
import id.buaja.kmm_meal.domain.model.DetailMeal
import io.kamel.image.*
import io.ktor.http.Url

data class DetailsMealScreen(
    val idMeal: String
) : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val detailScreenModel = getScreenModel<DetailsMealScreenModel>()

        val state by detailScreenModel.state.collectAsState()

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = {
                        navigator.pop()
                    }
                ) {
                    Icon(
                        imageVector = Icons.Rounded.ArrowBackIosNew,
                        contentDescription = null
                    )
                }

                Text(
                    text = "Details Meal",
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.Bold
                )
            }

            when (val result = state) {
                DetailsMealState.Loading -> {
                    MealLoading()
                }

                is DetailsMealState.Success -> {
                    DetailsMealContent(
                        detailMeal = result.data
                    )
                }

                is DetailsMealState.Error -> {
                    MealAlert(
                        title = "Ops!",
                        message = result.message,
                        buttonText = "Retry",
                        onButtonClick = {
                            detailScreenModel.getDetailByIdeMeal(
                                idMeal = idMeal
                            )
                        }
                    )
                }
            }
        }

        LaunchedEffect(currentCompositeKeyHash) {
            detailScreenModel.getDetailByIdeMeal(
                idMeal = idMeal
            )
        }
    }
}

@Composable
fun DetailsMealContent(
    detailMeal: DetailMeal,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        KamelImage(
            resource = asyncPainterResource(
                Url(detailMeal.strImageSource)
            ),
            contentDescription = "MealThumb"
        )

        Spacer(
            modifier = Modifier
                .height(16.dp)
        )

        Text(
            text = "Area: ${detailMeal.strArea}",
            style = MaterialTheme.typography.h6
        )

        Text(
            text = "Category: ${detailMeal.strCategory}",
            style = MaterialTheme.typography.body1
        )

        Spacer(modifier = Modifier.height(8.dp))

        if (detailMeal.strInstructions.isNotEmpty()) {
            Text(
                text = "Instructions: ${detailMeal.strInstructions}",
                style = MaterialTheme.typography.body2
            )
        }

        if (detailMeal.strCreativeCommonsConfirmed.isNotEmpty()) {
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Creative Commons Confirmed: ${detailMeal.strCreativeCommonsConfirmed}",
                style = MaterialTheme.typography.body2
            )
        }

        if (detailMeal.strDrinkAlternate.isNotEmpty()) {
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Drink Alternate: ${detailMeal.strDrinkAlternate}",
                style = MaterialTheme.typography.body2
            )
        }
    }
}