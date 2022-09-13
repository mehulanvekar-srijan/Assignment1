package com.assignment.one.ui.screens

import android.content.Context
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.assignment.one.*
import com.assignment.one.R
import com.assignment.one.data.user
import com.assignment.one.ui.theme.Assignment1Theme
import com.assignment.one.ui.theme.Typography

//Splash Screen
@Composable
fun Splash(text: String = "Android-Default") {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = text, color = Color.Cyan, style = Typography.h5)
    }
}

//Main Activity
@Composable
fun MainActivityTheme(){
    val userNameList = mutableListOf("User Name","Enter User Name")
    val passwordList = mutableListOf("Password","Enter Password")

    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.Yellow),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val openDialog = remember { mutableStateOf(false) }

        DrawCredentialRows(list = userNameList,type = "un")      // User Name | Enter User name
        DrawCredentialRows(list = passwordList,type = "p")       // Password  | Enter Password
        Button(onClick = {

            convertToObject()
            val status = validate(user.userName, user.password)

            if(status) openDialog.value = false
            else openDialog.value = true

        }) {
            Text(text = "Lets Go")
        }      // Button : Lets Go

        if(openDialog.value){
            AlertDialog(
                onDismissRequest = {
                    openDialog.value = false
                },
                title = {
                    Text(text = "Hello ${user.userName}")
                },
                text = {
                    Text("Incorrect username or password")
                },
                confirmButton = {
                    Button(onClick = {
                            openDialog.value = false
                        }) { Text("Ok") }
                },
            )
        }
    }
}

@Composable
fun DrawCredentialRows(list: List<String>,type: String){
    Row(modifier = Modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Column(modifier = Modifier.padding(10.dp)){ Text(text = list[0]) }
        Column(modifier = Modifier.padding(10.dp)){

            val input = remember { mutableStateOf("") }
            TextField(
                value = input.value, onValueChange = { input.value = it },
                label = { Text(text = list[1]) }
            )

            if(type == "un") user.userName = input.value
            else user.password = input.value
        }
    }
}

//Home Screen
@Composable
fun HomeScreenTheme(){
    val numberOfRows = 2
    convertToArray()

    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.Cyan)) {
        for (i in 1..numberOfRows) DrawHomeRows()
    }
}

@Composable
fun DrawHomeRows(){
    Row(horizontalArrangement = Arrangement.SpaceAround){
        index++
        Column(){
            Text(text = productList[index].productName)
            Image(painter = painterResource(id = productList[index].imageUrl.toInt()), contentDescription = "")
        }

        index++
        Column {
            Text(text = productList[index].productName)
            Image(painter = painterResource(id = productList[index].imageUrl.toInt()), contentDescription = "")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Assignment1Theme {
        HomeScreenTheme()
    }
}