package com.assignment.one

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.assignment.one.ui.theme.Assignment1Theme
import com.assignment.one.utils.Navigation
import com.assignment.one.viewmodel.HomeScreenViewModel
import com.assignment.one.viewmodel.LoginViewModel
import com.assignment.one.viewmodel.SplashScreenViewModel

class MainActivity : ComponentActivity() {

    /*
        If I don't use 'by' keyword the HomeScreen send request to the API again
        + input values are lost on config change
    */
    private val loginViewModel: LoginViewModel by viewModels()
    private val splashScreenViewModel: SplashScreenViewModel by viewModels()
    private val homeScreenViewModel: HomeScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        //Call Navigation Controller which shows Splash() as first screen
        setContent{

            val navHostController = rememberNavController()

            val navHostControllerLambda : () -> NavHostController = { navHostController }
            val splashScreenViewModelLambda : () -> SplashScreenViewModel = { splashScreenViewModel }
            val loginViewModelLambda : () -> LoginViewModel = { loginViewModel }
            val homeScreenViewModelLambda : () -> HomeScreenViewModel = { homeScreenViewModel }


            Assignment1Theme(){
                Navigation(
                    navController = navHostController,

                    navHostControllerLambda = navHostControllerLambda,
                    splashScreenViewModelLambda = splashScreenViewModelLambda,
                    loginViewModelLambda = loginViewModelLambda,
                    homeScreenViewModelLambda = homeScreenViewModelLambda,
                )
            }

        }
    }
}