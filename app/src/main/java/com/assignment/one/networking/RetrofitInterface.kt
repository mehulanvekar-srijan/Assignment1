package com.assignment.one.networking

import com.assignment.one.domain.model.Product
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitInterface {

    @GET("/products")
    fun getProductData(): Call<List<Product>>

}