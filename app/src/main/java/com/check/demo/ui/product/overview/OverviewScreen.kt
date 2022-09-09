package com.check.demo.ui.product.overview

import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.check.demo.R
import com.check.demo.datasource.model.ProductInfo
import com.check.demo.ui.views.Price
import com.check.demo.ui.views.Rate
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.squareup.moshi.Moshi
import org.koin.androidx.compose.get

@Composable
fun OverviewScreen(viewModel: OverviewViewModel, navController: NavController) {
    val uiState by viewModel.uiState.collectAsState()
    val moshi = get<Moshi>()
    OverviewScreen(
        uiState = uiState,
        onItemClick = {
            val json = Uri.encode(moshi.adapter(ProductInfo::class.java).toJson(it))
            navController.navigate("details/$json")
        },
        onRefreshCallback = { viewModel.refresh() })
}

@Composable
fun OverviewScreen(
    uiState: OverviewViewModel.UiState,
    onItemClick: (ProductInfo) -> Unit,
    onRefreshCallback: () -> Unit
) {
    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing = uiState.isRefreshing),
        onRefresh = { onRefreshCallback.invoke() }
    ) {
        if (uiState.error != null) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .verticalScroll(rememberScrollState()),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Something went wrong :(")
            }
        } else {
            if (uiState.isLoading) {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else {
                Content(uiState, onItemClick)
            }
        }
    }
}

@Composable
private fun Content(
    uiState: OverviewViewModel.UiState,
    onItemClick: (ProductInfo) -> Unit
) {
    Column(Modifier.padding(16.dp)) {
        LazyColumn(Modifier.padding(vertical = 4.dp)) {
            uiState.overviewInfo?.let {
                item {
                    Column {
                        Text(it.title, fontWeight = FontWeight.Bold)
                        Text(it.subtitle)
                    }
                }
            }
            items(uiState.data) {
                ProductOverviewItem(
                    productInfo = it,
                    onItemClick
                )
            }
            item {
                Text(text = stringResource(id = R.string.legal_info))
            }
        }
    }
}

@Composable
fun ProductOverviewItem(productInfo: ProductInfo, onClick: (ProductInfo) -> Unit) {
    Card(modifier = Modifier
        .padding(8.dp)
        .fillMaxWidth()
        .clickable { onClick.invoke(productInfo) }) {
        Row(
            modifier = Modifier
                .padding(8.dp)
        ) {
            AsyncImage(
                model =
                productInfo.iconUrl, contentDescription = null,
                modifier = Modifier
                    .width(64.dp)
                    .height(64.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Row {
                    Text(text = productInfo.name, fontWeight = FontWeight.Bold)
                    Spacer(Modifier.weight(1f))
                    Text(text = productInfo.date)
                }
                Text(text = productInfo.description)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Price(productInfo.price, productInfo.currency)
                    Spacer(Modifier.weight(1f))
                    Rate(productInfo.rating)
                }
            }
        }
    }
}

@Preview
@Composable
fun ProductOverviewItemPreview() {
    ProductOverviewItem(
        productInfo = ProductInfo(
            0,
            "Name",
            "",
            "10",
            "$",
            "1000",
            "Description",
            "",
            4.5
        )
    ) {}
}
