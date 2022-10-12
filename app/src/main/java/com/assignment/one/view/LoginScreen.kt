package com.assignment.one.view

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.assignment.one.viewmodel.LoginViewModel
import com.assignment.one.ui.theme.*

@Composable
fun LogInScreenTheme(
    navHostControllerLambda : () -> NavHostController,
    loginViewModel: LoginViewModel = viewModel(),
) {

//    val loginViewModel = remember{ LoginViewModel() }

    Log.d("textMX", "LogInScreen: compose")
    Log.d("testPerf", "LogInScreen: compose")

    Column(verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(LoginBackground),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //User Name
        DrawUserName(loginViewModel = loginViewModel)

        //Password
        DrawPassword(loginViewModel = loginViewModel)

        //LetsGo Button
        LetsGoButton(navHostControllerLambda(),loginViewModel)

        if(loginViewModel.openDialog.value){
            DrawAlertDialog(loginViewModel = loginViewModel)
        }
    }
}

@Composable
fun DrawUserName(loginViewModel: LoginViewModel) {
    Log.d("testPerf", "DrawUserName: called")
    Row(modifier = Modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Column(modifier = Modifier.padding(10.dp)){
            Text(text = "User Name", color = LoginText, textAlign = TextAlign.Center)
        }
        Column(modifier = Modifier.padding(10.dp)){
            OutlinedTextField(
                value = loginViewModel.userNameState.value,
                onValueChange = {
                    loginViewModel.onUserNameValueChange(it)
                },
                label = { Text(text = "Enter user name") },
            )
        }
    }
}

@Composable
fun DrawPassword(loginViewModel: LoginViewModel) {
    Log.d("testPerf", "DrawPassword: called")
    Row(modifier = Modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Column(modifier = Modifier.padding(10.dp)){
            Text(text = "Password ", color = LoginText, textAlign = TextAlign.Center)
        }
        Column(modifier = Modifier.padding(10.dp)){
            OutlinedTextField(
                value = loginViewModel.password.value,
                onValueChange = {
                    loginViewModel.onPasswordValueChange(it)
                },
                visualTransformation = if(loginViewModel.passwordVisibility.value) VisualTransformation.None else PasswordVisualTransformation(),
                label = { Text(text = "Enter password") },
                trailingIcon = {
                    IconButton(onClick = { loginViewModel.onPasswordVisibilityChange() }) {
                        if(loginViewModel.passwordVisibility.value)
                            Icon(imageVector = Icons.Rounded.Lock, contentDescription = "", tint = Color.Red)
                        else
                            Icon(imageVector = Icons.Rounded.Lock, contentDescription = "", tint = Purple700)
                    }
                },
            )
        }
    }
}

@Composable
fun LetsGoButton(navController: NavHostController, loginViewModel: LoginViewModel) {
    Button(
        onClick = { loginViewModel.onClickButton(navController) },
        shape = RoundedCornerShape(50),
        modifier = Modifier
            .fillMaxWidth(0.5F)
            .padding(horizontal = 20.dp)
            .padding(top = 5.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = LoginButtonBackground,
            contentColor = LoginButtonText
        ),
    ) { Text(text = "Lets Go") }      // Button : Lets Go

}

@Composable
fun DrawAlertDialog(loginViewModel: LoginViewModel) {
    AlertDialog(
        onDismissRequest = {
            loginViewModel.onDialogResponseChange(false)
        },
        title = {
            Text(text = "Hello ${loginViewModel.userNameState.value}")
        },
        text = {
            Text("Incorrect username or password")
        },
        confirmButton = {
            Button(onClick = {
                loginViewModel.onDialogResponseChange(false)
            }) { Text("Ok") }
        },
    )
}