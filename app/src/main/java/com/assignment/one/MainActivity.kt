package com.assignment.one

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.assignment.one.data.user
import com.assignment.one.navigation.Navigation
import com.assignment.one.ui.screens.HomeScreenTheme
import com.assignment.one.ui.screens.MainActivityTheme
import kotlin.concurrent.thread

class MainActivity : ComponentActivity() {

    val context = this
    lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //STEP1: Show splash screen
        setContent{
            navController = rememberNavController()
            Navigation(navController = navController,context = this)
        }
    }
}