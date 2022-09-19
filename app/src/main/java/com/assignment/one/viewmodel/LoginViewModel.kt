package com.assignment.one.viewmodel

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.assignment.one.domain.model.User
import com.assignment.one.domain.repository.readJsonFromAssets
import com.assignment.one.domain.repository.saveData
import com.assignment.one.utils.Screen
import com.google.gson.Gson

class LoginViewModel : ViewModel() {

    private var incomingUser = User("","")
    private var apiUser = User("","")

    private val _openDialog: MutableState<Boolean> =  mutableStateOf(false)
    val openDialog: State<Boolean> = _openDialog

    private val _userNameState: MutableState<String> = mutableStateOf("")
    val userNameState: State<String> = _userNameState

    private val _password: MutableState<String> = mutableStateOf("")
    val password : State<String> = _password

    fun onUserNameValueChange(input: String){
        _userNameState.value = input
    }

    fun onPasswordValueChange(input: String){
        _password.value = input
    }

    fun onDialogResponseChange(input: Boolean){
        _openDialog.value = input
    }

    fun onClickButton(context: Context, navController: NavHostController){
        saveInputData() //STEP1: Save TextField data into incomingUser object

        val userJson = readJsonFromAssets(context)  //Read JSON object from assets (Local Repo)

        convertToObject(userJson)   //Convert it to Kotlin object and save it in apiUser variable

        val status =  validate() // compare incomingUser & apiUser object

        if(status) { //User is valid
            _openDialog.value = false
            saveData(context, incomingUser.userName, incomingUser.password)
            navigate(navController)
        }
        else _openDialog.value = true
    }

    private fun saveInputData(){
        incomingUser.userName = _userNameState.value
        incomingUser.password = _password.value
    }

    private fun convertToObject(userJson: String){
        val gson = Gson()
        apiUser = gson.fromJson(userJson, User::class.java)
    }

    private fun validate(): Boolean{
        return (incomingUser.userName == apiUser.userName && incomingUser.password == apiUser.password)
    }

    private fun navigate(navController: NavHostController){
        navController.navigate(Screen.HomeScreen.route){
            popUpTo(Screen.LogInScreen.route) { inclusive = true }
        }
    }
}

