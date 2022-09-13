package com.assignment.one

import android.util.Log
import com.assignment.one.data.User
import com.assignment.one.extra.Product
import com.assignment.one.extra.data
import com.assignment.one.extra.json
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

lateinit var apiUser: User

fun convertToObject(){
    val gson = Gson()
    apiUser = gson.fromJson(json, User::class.java)
}

var index = -1
lateinit var productList: Array<Product>
fun convertToArray(){
    var gson = Gson()
    val typeToken = object : TypeToken<Array<Product>>(){}.type
    productList = gson.fromJson(data, typeToken)
    for (value in productList){
        Log.d("Mehul", "convertToArray: ${value.productName}")
    }
}

//returns true if validation is successful else false
fun validate(inputName:String , inputPassword: String): Boolean{
    return (inputName == apiUser.userName && inputPassword == apiUser.password)
}