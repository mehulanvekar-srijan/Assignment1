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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.assignment.one.domain.repository.remoteRepository

//Home Screen
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreenTheme(){

    val newList = remoteRepository.getProductList().shuffled()

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
                        modifier = Modifier.background(
                            Brush.verticalGradient(colors = listOf(Color.Transparent,Color.DarkGray),
                                startY = 400f))) {
                        Text(text = newList[index].productName, modifier = Modifier.padding(5.dp), color = Color.White)
                    }
                }
            }
        }
    )
}
