package com.assignment.one.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.assignment.one.domain.repository.localRepository
import com.assignment.one.local.loadData
import com.assignment.one.domain.repository.remoteRepository
import com.assignment.one.utils.Screen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenViewModel : ViewModel() {

    private var timer = 1

    fun execute(scope: CoroutineScope, context: Context, navController: NavHostController){
        scope.launch {

            //Load user date from shared pref
            val user = localRepository.loadUserData(context)

            //Ask Repo to fetch JSON data
            remoteRepository.fetchFromServer()

            //Wait for 5 sec for the productList to get filled
            while(timer <= 5) {
                delay(1000)
                if(remoteRepository.getProductList().isEmpty()) {
                    timer ++
                    continue
                }
                else break
            }

            //If productList is still empty go to Error Screen
            if(remoteRepository.getProductList().isEmpty()){
                navController.navigate(Screen.NoNetworkScreen.route){
                    popUpTo(Screen.MainScreen.route) { inclusive = true }
                }
            }
            else { //Else go to LoginScreen / HomeScreen
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


}