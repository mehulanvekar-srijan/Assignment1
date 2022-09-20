package com.assignment.one.view

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.assignment.one.viewmodel.LoginViewModel

@Composable
fun LogInScreenTheme(context: Context, navController: NavHostController, loginViewModel: LoginViewModel) {

    Column(verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        //User Name
        Row(modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Column(modifier = Modifier.padding(10.dp)){
                Text(text = "User Name", color = Color.Black, textAlign = TextAlign.Center)
            }
            Column(modifier = Modifier.padding(10.dp)){
                TextField(
                    value = loginViewModel.userNameState.value,
                    onValueChange = {
                        loginViewModel.onUserNameValueChange(it)
                    },
                    label = { Text(text = "Enter user name") }
                )
            }
        }

        //Password
        Row(modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Column(modifier = Modifier.padding(10.dp)){
                Text(text = "Password", color = Color.Black, textAlign = TextAlign.Center)
            }
            Column(modifier = Modifier.padding(10.dp)){
                TextField(
                    value = loginViewModel.password.value,
                    onValueChange = {
                        loginViewModel.onPasswordValueChange(it)
                    },
                    visualTransformation = PasswordVisualTransformation(),
                    label = { Text(text = "Enter password") }
                )
            }
        }

        Button(onClick = { loginViewModel.onClickButton(context,navController) }
        ) { Text(text = "Lets Go") }      // Button : Lets Go

        if(loginViewModel.openDialog.value){
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
    }
}