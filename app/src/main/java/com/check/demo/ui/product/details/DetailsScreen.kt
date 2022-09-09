package com.check.demo.ui.product.details

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.check.demo.R
import com.check.demo.ui.views.Price
import com.check.demo.ui.views.Rate

@Composable
fun DetailsScreen(viewModel: DetailsViewModel, navController: NavController) {
    val state by viewModel.uiState.collectAsState()
    DetailScreen(uiState = state)
}

@Composable
fun DetailScreen(uiState: DetailsViewModel.UiState) {
    val scrollState = rememberScrollState()
    Column(
        Modifier
            .padding(16.dp, 16.dp)
            .fillMaxHeight()
            .fillMaxWidth()
            .scrollable(state = scrollState, orientation = Orientation.Vertical)
    ) {
        Row {
            AsyncImage(
                model = uiState.iconUrl,
                contentDescription = null,
                Modifier
                    .width(64.dp)
                    .height(64.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(text = uiState.name)
                Price(uiState.price, uiState.currency)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    uiState.rate?.let { Rate(it) }
                    Spacer(Modifier.weight(1f))
                    uiState.date?.let { Text(text = it) }
                }
            }
        }
        uiState.description?.let {
            Column {
                Text(
                    stringResource(id = R.string.short_description_title),
                    fontWeight = FontWeight.Bold
                )
            }
            Text(text = it)
        }
        Spacer(Modifier.height(8.dp))
        Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Button(onClick = { /*TODO*/ }) {
                Text(stringResource(id = R.string.favorite_button_title))
            }
        }
        Spacer(Modifier.height(8.dp))
        uiState.longDescription?.let {
            Column {
                Text(
                    stringResource(id = R.string.long_description_title),
                    fontWeight = FontWeight.Bold
                )
                Text(text = it)
            }
        }
        Spacer(Modifier.height(16.dp))
        Text(text = stringResource(id = R.string.legal_info))
    }

}
