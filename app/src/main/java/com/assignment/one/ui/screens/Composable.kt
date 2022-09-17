package com.assignment.one.ui.screens

import android.app.Person
import android.content.Context
import android.graphics.fonts.FontStyle
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.assignment.one.*
import com.assignment.one.data.Product
import com.assignment.one.data.loadData
import com.assignment.one.data.saveData
import com.assignment.one.data.user
import com.assignment.one.navigation.Screen
import com.assignment.one.ui.theme.Assignment1Theme
import com.assignment.one.ui.theme.Typography
import kotlinx.coroutines.*
import java.lang.NullPointerException

//Splash Screen
@Composable
fun Splash(text: String = "Android-Default",context: Context,navController: NavHostController) {
    //STEP1: Draw Screen Views
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = text, color = Color.Cyan, style = Typography.h5)
        CircularProgressIndicator(modifier = Modifier.padding(50.dp), color = Color.Cyan)
    }

    LaunchedEffect(key1 = true){
        //STEP2: Read JSON from file
        readJsonFromAssets(context)

        //STEP3: Check if user is registered
        loadData(context) //Load SharedPref value of the user

        delay(2000)

        /* STEP4:
         * IF the userName and password is empty,
         * That means user is not registered, Hence show login page
         * And then save the user credentials in SharedPrefs
         *
         * ELSE directly redirect to Home screen
         * as the user is already registered
         */
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

//Main Activity
@Composable
fun MainActivityTheme(context: Context,navController: NavHostController){

    val userNameList = mutableListOf("User Name","Enter User Name")
    val passwordList = mutableListOf("Password","Enter Password")

    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.White),
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
                navController.navigate(Screen.HomeScreen.route){
                    popUpTo(Screen.LogInScreen.route) { inclusive = true }
                }
            }
            else openDialog.value = true

        }
        ) {
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
                label = { Text(text = list[1]) },
                visualTransformation = if(type == "p") PasswordVisualTransformation()
                                       else VisualTransformation.None,
                )

            if(type == "un") user.userName = input.value
            else user.password = input.value
        }
    }
}

//Home Screen
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreenTheme(){

    var newList = productList?.shuffled() ?: listOf<Product>()

    if(newList.isEmpty()){
        NoNetworkConnectionError()
    }
    else {
        LazyVerticalGrid(
            cells = GridCells.Fixed(2),
            modifier = Modifier.background(Color.White),
            content = {
                items(newList.size){ index ->
                    Card(modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        .padding(5.dp),
                        elevation = 10.dp,
                        shape = RoundedCornerShape(8.dp),
                    ) {
                        Image(painter = rememberImagePainter(newList[index].imageUrl),
                            contentDescription = "",
                            contentScale = ContentScale.Fit)

                        Box(contentAlignment = Alignment.BottomCenter,
                            modifier = Modifier.background(Brush.verticalGradient(colors = listOf(Color.Transparent,Color.DarkGray),
                                startY = 400f))) {
                            Text(text = newList[index].productName, modifier = Modifier.padding(5.dp), color = Color.White)
                        }
                    }
                }
            })
    }

}

@Composable
fun NoNetworkConnectionError() {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Red),
        contentAlignment = Alignment.Center,
    ) {
        Text(text = "Please Connect To Network", color = Color.White, fontSize = 50.sp, textAlign = TextAlign.Center)
    }
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Assignment1Theme {
        //NoNetworkConnectionError()
        HomeScreenTheme()
    }
}