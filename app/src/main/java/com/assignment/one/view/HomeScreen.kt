package com.assignment.one.view

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.assignment.one.networking.NetworkStatus
import com.assignment.one.ui.theme.*
import com.assignment.one.viewmodel.HomeScreenViewModel

//Home Screen
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreenTheme(homeScreenViewModel: HomeScreenViewModel = viewModel()){

    Log.d("textMX", "HomeScreen: compose")

    LaunchedEffect(key1 = true){
        if(homeScreenViewModel.productListSate.value.isEmpty()) homeScreenViewModel.execute()
    }

    if(homeScreenViewModel.networkStatusState.value == NetworkStatus.Fetching) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            CircularProgressIndicator()
            Text(text = "Fetching..")
        }
    }
    if(homeScreenViewModel.networkStatusState.value == NetworkStatus.Success){
        LazyVerticalGrid(
            cells = GridCells.Fixed(2),
            modifier = Modifier
                .background(Color.White)
                .fillMaxSize(),
            content = {
                items(homeScreenViewModel.productListSate.value.size){ index ->
                    Card(modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        .padding(5.dp),
                        elevation = 19.dp,
                        shape = RoundedCornerShape(5.dp),
                    ) {
                        Image(painter = rememberImagePainter(homeScreenViewModel.productListSate.value[index].imageUrl),
                            contentDescription = "",
                            contentScale = ContentScale.Fit)

                        Box( modifier = Modifier.padding(0.dp),
                            contentAlignment = Alignment.BottomCenter,
                        ) {
                            Text(text = homeScreenViewModel.productListSate.value[index].productName,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .fillMaxHeight(0.2F)
                                    .fillMaxWidth()
                                    .background(
                                        brush = Brush.verticalGradient(
                                            colors = listOf(
                                                NavyBlazer,
                                                Inkwell
                                            )
                                        )
                                    )
                                    .padding(3.dp),
                                color = Color.White,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis,
                            )
                        }
                    }
                }
            }
        )
    }
    if(homeScreenViewModel.networkStatusState.value == NetworkStatus.Failed) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(text = "Failed..", color = Color.Red, fontSize = 30.sp)
        }
    }
}

