package com.assignment.one.navigation

import android.content.Context
import android.window.SplashScreen
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.assignment.one.data.loadData
import com.assignment.one.data.user
import com.assignment.one.ui.screens.HomeScreenTheme
import com.assignment.one.ui.screens.MainActivityTheme
import com.assignment.one.ui.screens.Splash
import kotlin.concurrent.thread

sealed class Screen(val route:String){
    object MainScreen : Screen("MainScreen")     //Splash Theme
    object LogInScreen : Screen("LogInScreen")   // Main Activity Theme
    object HomeScreen : Screen("HomeScreen")     // Home Screen Theme
}

@Composable
fun Navigation(navController: NavHostController,context: Context) {
    NavHost(
        navController = navController,
        startDestination = Screen.MainScreen.route
    ){
        composable(route = Screen.MainScreen.route){ Splash("Mehul",context,navController) }
        composable(route = Screen.LogInScreen.route){ MainActivityTheme(context = context, navController = navController) } //
        composable(route = Screen.HomeScreen.route){ HomeScreenTheme() }
    }
}

