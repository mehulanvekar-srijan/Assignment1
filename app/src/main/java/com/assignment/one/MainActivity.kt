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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        //Call Navigation Controller which shows Splash() as first screen
        setContent{
            Assignment1Theme(){
                Navigation()
            }

        }
    }
}