package com.assignment.one.ui.screens

import android.content.Context
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.assignment.one.*
import com.assignment.one.data.loadData
import com.assignment.one.data.saveData
import com.assignment.one.data.user
import com.assignment.one.extra.imageArray
import com.assignment.one.navigation.Screen
import com.assignment.one.ui.theme.Assignment1Theme
import com.assignment.one.ui.theme.Typography
import kotlin.concurrent.thread

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
fun MainActivityTheme(context: Context){
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

            //STEP1: Fetch Credentials from Remote Database
            convertToObject()

            //STEP2: Validate the user input with the fetched credentials
            val status = validate(user.userName, user.password)

            /*
            * STEP3: if validation is successful save the credentials in a SharedPref
            *        turnoff AlertDialog
            *        redirect to Home Screen
            * */
            if(status) {
                openDialog.value = false
                saveData(context, user.userName, user.password)
                //Redirect
            }
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

    convertToArray() //Convert JSON to Kotlin Array

    //Main Column
    Column(modifier = Modifier.fillMaxSize().background(Color.Black)) {
        for (i in 1..numberOfRows) DrawHomeRows()
    }
}

@Composable
fun DrawHomeRows(){
    Row(){
        Box(modifier = Modifier.fillMaxWidth(0.5f).padding(10.dp)) {
            DrawHomeEachColumn()
        }
        Box(modifier = Modifier.padding(10.dp)) {
            DrawHomeEachColumn()
        }
    }
}

@Composable //Generates Each individual card in of each column in a row
fun DrawHomeEachColumn(){
    index++
    Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(8.dp)) {
        Column(
            modifier = Modifier.height(200.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = imageArray[index]),
                contentDescription = "",
                contentScale = ContentScale.Fit)
            Text(text = productList[index].productName)
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