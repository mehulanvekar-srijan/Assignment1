package com.assignment.one.utils

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.assignment.one.ui.theme.Assignment1Theme
import com.assignment.one.ui.theme.NoStatusBarTheme
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
    context: Context,
    loginViewModel: LoginViewModel,
    splashScreenViewModel: SplashScreenViewModel,
    homeScreenViewModel: HomeScreenViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Screen.MainScreen.route
    ){
        composable(route = Screen.MainScreen.route){
            Assignment1Theme{
                Splash("Mehul",context,navController,splashScreenViewModel)
            }
        }
        composable(route = Screen.LogInScreen.route){
            NoStatusBarTheme{
                LogInScreenTheme(context,navController,loginViewModel)
            }
        }
        composable(route = Screen.HomeScreen.route){
            NoStatusBarTheme{
                HomeScreenTheme(homeScreenViewModel)
            }
        }
    }
}
