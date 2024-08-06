package id.buaja.kmm_meal.screens.detailsmeal

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.currentCompositeKeyHash
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import coil3.compose.AsyncImage
import id.buaja.kmm_meal.core.designsystem.component.MealAlert
import id.buaja.kmm_meal.core.designsystem.component.MealLoading
import id.buaja.kmm_meal.domain.model.DetailMeal

data class DetailsMealScreen(
    val idMeal: String
) : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val detailScreenModel = koinScreenModel<DetailsMealScreenModel>()

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
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp)),
            model = detailMeal.strImageSource,
            contentDescription = detailMeal.idMeal,
            contentScale = ContentScale.Crop
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