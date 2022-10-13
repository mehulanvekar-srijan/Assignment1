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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.sharp.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.assignment.one.R
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

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "mShop") },
                backgroundColor = DarkBlue,
                contentColor = White,
                elevation = 10.dp,
                actions = {
                    IconButton(onClick = {}) {
                        Icon(imageVector = Icons.Filled.Favorite,
                            contentDescription = "")
                    }
                    IconButton(onClick = {}) {
                        Icon(imageVector = Icons.Filled.ShoppingCart,
                            contentDescription = "")
                    }
                }
            )
        }
    ) {
        if(homeScreenViewModel.networkStatusState.value == NetworkStatus.Fetching) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize().background(DarkBlue)
            ) {
                CircularProgressIndicator(color = LightBlue)
                Text(text = "Fetching..", color = LightBlue, fontSize = 30.sp, style = Typography.h5)
            }
        }
        if(homeScreenViewModel.networkStatusState.value == NetworkStatus.Success){
            LazyVerticalGrid(
                cells = GridCells.Fixed(2),
                modifier = Modifier
                    .background(DarkBlue)
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

                            Box(
                                modifier = Modifier.padding(0.dp),
                                contentAlignment = Alignment.BottomCenter,
                            ) {
                                Text(
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier
                                        .fillMaxHeight(0.2F)
                                        .fillMaxWidth()
                                        .background(color = LightBlue)
                                        .padding(3.dp),
                                    color = Color.White,
                                    fontSize = 16.sp,
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis,
                                    text = homeScreenViewModel.productListSate.value[index].productName,
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
                modifier = Modifier.fillMaxSize().background(DarkBlue)
            ) {
                Text(text = "Failed..", color = Color.Red, fontSize = 30.sp, style = Typography.h5)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "mShop") },
                backgroundColor = DarkBlue,
                contentColor = White,
                actions = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Filled.Favorite,
                            contentDescription = ""
                        )
                    }
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Filled.ShoppingCart,
                            contentDescription = "",
                        )
                    }
                }
            )
        }
    ){
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(LightBlue)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_background),
                contentDescription = "",
                modifier = Modifier
                    .padding(0.dp)
                    .fillMaxSize(),
                contentScale = ContentScale.Crop,
            )

        }
    }
}