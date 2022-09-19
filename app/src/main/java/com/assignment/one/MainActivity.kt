package com.assignment.one

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.assignment.one.networking.HttpClient
import com.assignment.one.utils.Navigation
import com.assignment.one.viewmodel.LoginViewModel
import com.assignment.one.viewmodel.SplashScreenViewModel

class MainActivity : ComponentActivity() {

    val loginViewModel: LoginViewModel by viewModels()
    val splashScreenViewModel: SplashScreenViewModel by viewModels()

    lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Call Navigation Controller which shows Splash() as first screen
        setContent{
            navController = rememberNavController()
            Navigation(navController = navController,context = this,loginViewModel,splashScreenViewModel)
        }

        HttpClient().getApiResponse()
    }
}