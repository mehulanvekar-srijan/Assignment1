package com.assignment.one

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.assignment.one.networking.HttpClient
import com.assignment.one.ui.theme.Assignment1Theme
import com.assignment.one.utils.Navigation
import com.assignment.one.viewmodel.HomeScreenViewModel
import com.assignment.one.viewmodel.LoginViewModel
import com.assignment.one.viewmodel.SplashScreenViewModel
import kotlinx.coroutines.CoroutineScope

class MainActivity : ComponentActivity() {

    private val loginViewModel: LoginViewModel by viewModels()
    private val splashScreenViewModel: SplashScreenViewModel by viewModels()
    private val homeScreenViewModel: HomeScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Call Navigation Controller which shows Splash() as first screen
        setContent{
            Assignment1Theme(){
                Navigation(
                    navController = rememberNavController(),
                    context = this,
                    loginViewModel = loginViewModel,
                    splashScreenViewModel = splashScreenViewModel,
                    homeScreenViewModel = homeScreenViewModel
                )
            }
        }
    }
}