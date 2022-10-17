package com.assignment.one.view

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.assignment.one.R
import com.assignment.one.ui.theme.*
import com.assignment.one.viewmodel.SplashScreenViewModel
import kotlinx.coroutines.newCoroutineContext

//Splash Screen
@Composable
fun Splash(text: String,
           navHostControllerLambda : () -> NavHostController,
           splashScreenViewModel: SplashScreenViewModel = viewModel(),
) {
    Log.d("testDisp", "Splash: called ${Thread.currentThread().name}")

    val lifecycle = LocalLifecycleOwner.current.lifecycle

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
        lifecycle.addObserver(splashScreenViewModel)
        splashScreenViewModel.execute(navHostControllerLambda())
    }

//    DisposableEffect(key1 = Unit){
//        lifecycle.addObserver(splashScreenViewModel)
//        splashScreenViewModel.execute(navHostControllerLambda())
//        onDispose { lifecycle.removeObserver(splashScreenViewModel) }
//    }

//    DisposableEffect(key1 = true){
//
//        Log.d("testDisp", "DisposableEffect: called")
//
//        val job = splashScreenViewModel.execute(navHostControllerLambda(),lifecycleOwner,scope)
//
//        onDispose {
//            Log.d("testDisp", "onDispose: called")
//            job.cancel()
//        }
//    }
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
    Text(
        text = "by $text",
        color = SplashScreenText,
        style = Typography.h1,
        modifier = Modifier.padding(bottom = 10.dp)
    )
}
