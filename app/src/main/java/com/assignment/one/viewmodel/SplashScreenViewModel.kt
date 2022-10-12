package com.assignment.one.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.assignment.one.domain.repository.LocalRepository
import com.assignment.one.utils.Screen
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenViewModel() : ViewModel() {

    fun execute(navController: NavHostController){
        viewModelScope.launch() {
            //Load user date from shared pref

            val user = LocalRepository.loadUserData(navController.context)

            delay(3000)

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