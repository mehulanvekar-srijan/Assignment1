package com.assignment.one.networking

import com.assignment.one.data.Product
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitInterface {

    @GET("/products")
    fun getProductData(): Call<List<Product>>

}