package com.assignment.one.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.assignment.one.utils.NetworkStatus
import com.assignment.one.viewmodel.HomeScreenViewModel

//Home Screen
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreenTheme(homeScreenViewModel: HomeScreenViewModel){

    homeScreenViewModel.execute(rememberCoroutineScope())

    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        LazyVerticalGrid(
            cells = GridCells.Fixed(2),
            modifier = Modifier.background(Color.White),
            content = {
                items(homeScreenViewModel.productListSate.value.size){ index ->
                    Card(modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        .padding(5.dp),
                        elevation = 10.dp,
                        shape = RoundedCornerShape(8.dp),
                    ) {
                        Image(painter = rememberImagePainter(homeScreenViewModel.productListSate.value[index].imageUrl),
                            contentDescription = "",
                            contentScale = ContentScale.Fit)

                        Box(contentAlignment = Alignment.BottomCenter,
                            modifier = Modifier.background(
                                Brush.verticalGradient(colors = listOf(Color.Transparent,Color.DarkGray),
                                    startY = 400f))) {
                            Text(text = homeScreenViewModel.productListSate.value[index].productName, modifier = Modifier.padding(5.dp), color = Color.White)
                        }
                    }
                }
            }
        )
        if(homeScreenViewModel.networkStatusState.value == NetworkStatus.Fetching) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator()
                Text(text = "Fetching..")
            }
        }
        if(homeScreenViewModel.networkStatusState.value == NetworkStatus.Failed) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "Failed..", color = Color.Red, fontSize = 30.sp)
            }
        }
    }


}
