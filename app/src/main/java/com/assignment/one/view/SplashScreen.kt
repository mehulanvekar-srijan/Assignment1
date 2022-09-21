package com.assignment.one.view

import android.content.Context
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.assignment.one.ui.theme.*
import com.assignment.one.ui.theme.Typography
import com.assignment.one.viewmodel.SplashScreenViewModel

//Splash Screen
@Composable
fun Splash(text: String = "Android-Default",
           context: Context,
           navController: NavHostController,
           splashScreenViewModel: SplashScreenViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SplashScreenBackground),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = text, color = SplashScreenText, style = Typography.h5)
    }
    Log.d("", "Splash")
    splashScreenViewModel.execute(rememberCoroutineScope(),context,navController)
}
