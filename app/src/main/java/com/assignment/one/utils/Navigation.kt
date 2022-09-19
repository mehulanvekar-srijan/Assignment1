package com.assignment.one.utils

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.assignment.one.view.HomeScreenTheme
import com.assignment.one.view.LogInScreenTheme
import com.assignment.one.view.NoNetworkConnectionError
import com.assignment.one.view.Splash
import com.assignment.one.viewmodel.LoginViewModel
import com.assignment.one.viewmodel.SplashScreenViewModel

sealed class Screen(val route:String){
    object MainScreen : Screen("MainScreen")     // Splash Theme
    object LogInScreen : Screen("LogInScreen")   // Main Activity Theme
    object HomeScreen : Screen("HomeScreen")     // Home Screen Theme
    object NoNetworkScreen : Screen("NoNetworkScreen")     // No Network Screen Theme
}

@Composable
fun Navigation(navController: NavHostController,context: Context, loginViewModel: LoginViewModel,splashScreenViewModel: SplashScreenViewModel) {
    NavHost(
        navController = navController,
        startDestination = Screen.MainScreen.route
    ){
        composable(route = Screen.MainScreen.route){ Splash("Mehul",context,navController,splashScreenViewModel) }
        composable(route = Screen.LogInScreen.route){ LogInScreenTheme(context = context, navController = navController,loginViewModel = loginViewModel) }
        composable(route = Screen.HomeScreen.route){ HomeScreenTheme() }
        composable(route = Screen.NoNetworkScreen.route){ NoNetworkConnectionError() }
    }
}
