package com.assignment.one.viewmodel

import android.util.Log
import androidx.lifecycle.*
import androidx.navigation.NavHostController
import com.assignment.one.domain.repository.LocalRepository
import com.assignment.one.utils.Screen
import kotlinx.coroutines.*

class SplashScreenViewModel() : ViewModel() , LifecycleEventObserver {

    lateinit var job : Job

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when(event){
            Lifecycle.Event.ON_DESTROY -> { if(::job.isInitialized) job.cancel() }
            else -> {}
        }
    }

    fun execute(navController: NavHostController) : Job {
        Log.d("testDisp", "execute: called ${Thread.currentThread().name}")
        job = viewModelScope.launch(Dispatchers.IO) {
            //Load user date from shared pref
            Log.d("testDisp", "launch: called ${Thread.currentThread().name}")

            val user = LocalRepository.loadUserData(navController.context)

            delay(3000)

            withContext(Dispatchers.Main){
                Log.d("testDisp", "withContext: called ${Thread.currentThread().name}")
                if (user.userName.isEmpty() && user.password.isEmpty()){
                    navController.navigate(Screen.LogInScreen.route){
                        popUpTo(Screen.MainScreen.route) { inclusive = true }
                    }
                }
                else {
                    navController.navigate(Screen.HomeScreen.route){
                        popUpTo(Screen.MainScreen.route) { inclusive = true }
                    }
                }
                Log.d("testDisp", "withContext: left")
            }
            Log.d("testDisp", "launch: left")
        }
        Log.d("testDisp", "execute: left")

        return job
    }

}