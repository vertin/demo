package com.check.demo.ui.views

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.check.demo.R

@Composable
fun Price(price: String, currency: String) {
    Text(
        text = stringResource(
            id = R.string.price_title,
            price,
            currency
        )
    )
}
