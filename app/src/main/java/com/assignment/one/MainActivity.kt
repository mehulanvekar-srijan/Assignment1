package com.assignment.one

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.assignment.one.data.loadData
import com.assignment.one.data.user
import com.assignment.one.navigation.Navigation
import com.assignment.one.navigation.Screen
import com.assignment.one.ui.screens.HomeScreenTheme
import com.assignment.one.ui.screens.MainActivityTheme
import com.assignment.one.ui.screens.Splash
import kotlin.concurrent.thread

class MainActivity : ComponentActivity() {

    val context = this
    lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //STEP1: Show splash screen
        setContent{
            //Splash("Mehul")
            navController = rememberNavController()
            Navigation(navController = navController)
        }

        //STEP2: Read JSON from file
        readJsonFromAssets(context)

        //STEP3: Check if user is registered
        loadData(context) //Load SharedPref value of the user

        thread {
            //TODO: use delay()
            Thread.sleep(2000) //STEP4: Block for 2 sec
            /* STEP5:
             * IF the userName and password is empty,
             * That means user is not registered, Hence show login page
             * And then save the user credentials in SharedPrefs
             *
             * ELSE directly redirect to Home screen
             * as the user is already registered
             */
            if (user.userName.isEmpty() && user.password.isEmpty()){
                context.setContent { MainActivityTheme(context = context) }
                //navController.navigate(Screen.MainScreen.route)
            }
            else {
                context.setContent { HomeScreenTheme() }
                //navController.navigate(Screen.HomeScreen.route)
            }
        }

    }
}