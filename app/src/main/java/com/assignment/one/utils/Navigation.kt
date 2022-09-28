package com.assignment.one.utils

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.assignment.one.view.HomeScreenTheme
import com.assignment.one.view.LogInScreenTheme
import com.assignment.one.view.Splash
import com.assignment.one.viewmodel.HomeScreenViewModel
import com.assignment.one.viewmodel.LoginViewModel
import com.assignment.one.viewmodel.SplashScreenViewModel

sealed class Screen(val route:String){
    object MainScreen : Screen("MainScreen")     // Splash Theme
    object LogInScreen : Screen("LogInScreen")   // Main Activity Theme
    object HomeScreen : Screen("HomeScreen")     // Home Screen Theme
}

@Composable
fun Navigation(
    navController: NavHostController,
    navHostControllerLambda : () -> NavHostController,
    splashScreenViewModelLambda : () -> SplashScreenViewModel,
    loginViewModelLambda : () -> LoginViewModel,
    homeScreenViewModelLambda : () -> HomeScreenViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Screen.MainScreen.route
    ){
        composable(route = Screen.MainScreen.route){
            Splash("Mehul",navHostControllerLambda,splashScreenViewModelLambda)
        }
        composable(route = Screen.LogInScreen.route){
            LogInScreenTheme(navHostControllerLambda,loginViewModelLambda)
        }
        composable(route = Screen.HomeScreen.route){
            HomeScreenTheme(homeScreenViewModelLambda)
        }
    }
}
