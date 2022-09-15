package com.assignment.one

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.assignment.one.data.Product
import com.assignment.one.navigation.Navigation
import com.assignment.one.networking.RetrofitInterface
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {

    val context = this
    lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Call Navigation Controller which shows Splash() as first screen
        setContent{
            navController = rememberNavController()
            Navigation(navController = navController,context = this)
        }

        getApiData()
    }

    fun getApiData(){
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://fakestoreapi.com/")
            .build()
            .create(RetrofitInterface::class.java)

        val retrofitData = retrofitBuilder.getProductData()

        retrofitData.enqueue(object : Callback<List<Product>?>{
            override fun onResponse(call: Call<List<Product>?>, response: Response<List<Product>?>){
                Log.d("RetFt", "onResponse: ${response.body().toString()}")
            }

            override fun onFailure(call: Call<List<Product>?>, t: Throwable) {
                Log.d("RetFt", "onFailure: $t")
            }

        })
    }
}