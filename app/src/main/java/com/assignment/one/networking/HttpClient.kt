package com.assignment.one.networking

import androidx.compose.runtime.MutableState
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
    fun getApiResponse(productList: MutableState<List<Product>>,networkState: MutableState<NetworkStatus>){
        retrofitData.enqueue(object : Callback<List<Product>?> {
            override fun onResponse(call: Call<List<Product>?>, response: Response<List<Product>?>){
                productList.value = response.body() ?: emptyList()
                networkState.value = NetworkStatus.Success
            }

            override fun onFailure(call: Call<List<Product>?>, t: Throwable) {
                productList.value =  emptyList()
                networkState.value = NetworkStatus.Failed
            }
        })
    }

}