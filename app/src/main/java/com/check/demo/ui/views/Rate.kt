package com.check.demo.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.check.demo.R
import kotlin.math.roundToInt

@Composable
fun Rate(rate: Double) {
    val full = rate.roundToInt()
    Row(
        modifier = Modifier
            .width(58.dp)
            .wrapContentHeight()
    ) {
        repeat(full) {
            Image(
                painter = rememberAsyncImagePainter(R.drawable.rate_full),
                contentDescription = null,
                modifier = Modifier
                    .width(10.dp)
                    .height(10.dp)
                    .padding(2.dp)
            )
        }
        if (rate - full.toDouble() > 0) {
            Image(
                painter = rememberAsyncImagePainter(R.drawable.rate_half),
                contentDescription = null,
                modifier = Modifier
                    .width(10.dp)
                    .height(10.dp)
                    .padding(2.dp)
            )
        }
    }
}

@Preview
@Composable
fun preview() {
    Rate(3.5)
}
