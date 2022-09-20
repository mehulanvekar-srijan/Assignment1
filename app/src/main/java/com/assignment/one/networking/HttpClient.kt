package com.assignment.one.networking

import com.assignment.one.domain.model.Product
import com.assignment.one.domain.repository.RemoteRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HttpClient{

    private val retrofitBuilder = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://fakestoreapi.com/")
        .build()
        .create(RetrofitInterface::class.java)

    private val retrofitData = retrofitBuilder.getProductData()

    //Fill data inside productList present in RemoteRepository, on failure fill empty list
    fun getApiResponse(){
        retrofitData.enqueue(object : Callback<List<Product>?> {
            override fun onResponse(call: Call<List<Product>?>, response: Response<List<Product>?>){
                RemoteRepository.setProductList(response.body() ?: emptyList()) // List of products or Empty list
            }

            override fun onFailure(call: Call<List<Product>?>, t: Throwable) {
                RemoteRepository.setProductList(listOf()) //Empty immutable List
            }
        })
    }

}