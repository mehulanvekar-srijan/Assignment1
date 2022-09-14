package com.assignment.one

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //STEP1: Show splash screen
        setContent{
            Splash(text = "Mehul")
        }

        thread {
            Thread.sleep(2000) //STEP2: Block for 2 sec

            //STEP3: Check if user is registered
            loadData(this) //Load SharedPref value of the user

            /* STEP4:
             * IF the userName and password is empty,
             * That means user is not registered, Hence show login page
             * And then save the user credentials in SharedPrefs
             *
             * ELSE directly redirect to Home screen
             * as the user is already registered
             * */
            if (user.userName.isEmpty() && user.password.isEmpty()){
                context.setContent{ MainActivityTheme(context) }
            }
            else{
                context.setContent{ HomeScreenTheme() }
            }
        }
    }
}