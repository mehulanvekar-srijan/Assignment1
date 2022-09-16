package com.assignment.one

import android.content.Context
import android.util.Log
import com.assignment.one.data.User
import com.assignment.one.data.Product
import com.assignment.one.extra.data
import com.assignment.one.extra.json
import com.assignment.one.networking.RetrofitInterface
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

lateinit var apiUser: User

//Converts JSON object to Kotlin object
fun convertToObject(){
    val gson = Gson()
    apiUser = gson.fromJson(json, User::class.java)
}

//Converts JSON Array of objects to Kotlin Array of objects (Of type Product)
var index = -1

//returns true if validation is successful else false
fun validate(inputName:String , inputPassword: String): Boolean{
    return (inputName == apiUser.userName && inputPassword == apiUser.password)
}

//Read JSON data stored in Assets folder
fun readJsonFromAssets(context: Context){

    //Read JSON object from asset for User
    json = context.assets.open("user.json").bufferedReader().use {
        it.readText()
    }
}

var productList: List<Product>? = null
fun getApiData(){

    val retrofitBuilder = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://fakestoreapi.com/")
        .build()
        .create(RetrofitInterface::class.java)

    val retrofitData = retrofitBuilder.getProductData()

    retrofitData.enqueue(object : Callback<List<Product>?> {
        override fun onResponse(call: Call<List<Product>?>, response: Response<List<Product>?>){
            Log.d("RetFt", "onResponse: ${response.body().toString()}")
            if(response.body() != null) productList = response.body()!!
            Log.d("RetFt", "onResponse: size = ${productList?.size}")
        }

        override fun onFailure(call: Call<List<Product>?>, t: Throwable) {
            Log.d("RetFt", "onFailure: $t")
        }
    })
}

