package com.assignment.one

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.assignment.one.data.Product
import com.assignment.one.navigation.Navigation
import com.assignment.one.networking.RetrofitInterface
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {

    lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Call Navigation Controller which shows Splash() as first screen
        setContent{
            navController = rememberNavController()
            Navigation(navController = navController,context = this)
        }
        getApiData()
    }
}