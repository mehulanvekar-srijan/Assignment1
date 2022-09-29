package com.assignment.one.view

import android.content.Context
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.assignment.one.ui.theme.*
import com.assignment.one.ui.theme.Typography
import com.assignment.one.viewmodel.SplashScreenViewModel

//Splash Screen
@Composable
fun Splash(text: String,
           navHostControllerLambda : () -> NavHostController,
           splashScreenViewModel: SplashScreenViewModel = viewModel()
) {

    Log.d("textMX", "Splash: compose")

    DrawBackground(text)

    LaunchedEffect(key1 = true){
        splashScreenViewModel.execute(navHostControllerLambda())
    }
}

@Composable
fun DrawBackground(text: String,) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SplashScreenBackground),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        DrawName(text)
    }
}

@Composable
fun DrawName(text: String) {
    Text(text = text, color = SplashScreenText, style = Typography.h5)
}
