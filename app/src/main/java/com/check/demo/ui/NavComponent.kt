package com.check.demo.ui

import android.os.Build
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.check.demo.datasource.model.ProductInfo
import com.check.demo.ui.navigation.navtype.ProductNavType
import com.check.demo.ui.product.details.DetailsScreen
import com.check.demo.ui.product.details.DetailsViewModel
import com.check.demo.ui.product.overview.OverviewScreen
import com.squareup.moshi.Moshi
import org.koin.androidx.compose.get
import org.koin.androidx.compose.getViewModel

@Composable
fun NavComponent(modifier: Modifier, navController: NavHostController) {
    val moshi = get<Moshi>()
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = "overview"
    ) {
        composable("overview") {
            OverviewScreen(viewModel = getViewModel(), navController)
        }
        composable(
            "details/{product}",
            arguments = listOf(navArgument("product") { type = ProductNavType(moshi) })
        ) {
            val productInfo = if (Build.VERSION.SDK_INT >= 33) {
                it.arguments?.getParcelable("product", ProductInfo::class.java)
            } else {
                it.arguments?.getParcelable("product")
            }
            val viewModel = getViewModel<DetailsViewModel>()
            productInfo?.let(viewModel::setProduct) ?: throw IllegalStateException()

            DetailsScreen(viewModel, navController)
        }
    }
}
