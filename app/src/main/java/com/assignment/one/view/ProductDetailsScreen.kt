package com.assignment.one.view

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.assignment.one.domain.model.Product

@OptIn(ExperimentalCoilApi::class)
@Composable
fun ProductDetailsScreenTheme(navHostControllerLambda : () -> NavHostController,) {
    val product = navHostControllerLambda().previousBackStackEntry?.savedStateHandle?.get<Product>("product")
                    ?: Product("N/A",0F,"","N/A")

    Column(verticalArrangement = Arrangement.Center) {
        Image(
            painter = rememberImagePainter(product.imageUrl),
            contentDescription = "",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5F)
        )
        Text(text = product.productName)
        Text(text = product.description)
        Text(text = product.price.toString())
    }
}