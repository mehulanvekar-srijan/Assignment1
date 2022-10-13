package com.assignment.one.view

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.assignment.one.R
import com.assignment.one.ui.theme.*
import com.assignment.one.viewmodel.SplashScreenViewModel

//Splash Screen
@Composable
fun Splash(text: String,
           navHostControllerLambda : () -> NavHostController,
           splashScreenViewModel: SplashScreenViewModel = viewModel()
) {
    Log.d("textMX", "Splash: compose")
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_background),
            contentDescription = "",
            modifier = Modifier
                .padding(0.dp)
                .fillMaxSize(),
            contentScale = ContentScale.Crop,
        )
        DrawBackground(text)
    }

    LaunchedEffect(key1 = true){
        splashScreenViewModel.execute(navHostControllerLambda())
    }
}

@Composable
fun DrawBackground(text: String,) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(SplashScreenBackground),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "",
            modifier = Modifier.fillMaxSize(),
        )
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(SplashScreenBackground),
        contentAlignment = Alignment.BottomCenter
    ) {
        DrawContent(text)
    }
}

@Composable
fun DrawContent(text: String) {
    Text(text = "by $text", color = SplashScreenText, style = Typography.h1)
}
