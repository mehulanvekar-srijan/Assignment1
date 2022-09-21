package com.assignment.one.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.assignment.one.domain.repository.LocalRepository
import com.assignment.one.utils.Screen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenViewModel : ViewModel() {

    fun execute(context: Context, navController: NavHostController){
        viewModelScope.launch {
            //Load user date from shared pref
            val user = LocalRepository.loadUserData(context)

            delay(2000)

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

        }
    }


}