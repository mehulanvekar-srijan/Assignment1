package com.assignment.one

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.assignment.one.ui.screens.HomeScreenTheme
import com.assignment.one.ui.screens.MainActivityTheme
import com.assignment.one.ui.screens.Splash
import kotlin.concurrent.thread

class MainActivity : ComponentActivity() {

    val context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            Splash(text = "Mehul")
        }

        thread {
            for (i in 1..2) Thread.sleep(1000) //Block for 2 sec
            context.setContent{
                MainActivityTheme()
            }
        }

    }
}