package com.assignment.one.utils

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
fun Navigation() {

    val navHostController = rememberNavController() // actual navHostController object
    val navHostControllerLambda : () -> NavHostController = { navHostController } // func that returns navHostController object

    NavHost(
        navController = navHostController,
        startDestination = Screen.MainScreen.route
    ){
        composable(route = Screen.MainScreen.route){
            Splash("Mehul",navHostControllerLambda)
        }
        composable(route = Screen.LogInScreen.route){
            LogInScreenTheme(navHostControllerLambda)
        }
        composable(route = Screen.HomeScreen.route){
            HomeScreenTheme()
        }
    }
}
