package com.assignment.one.networking

import android.util.Log
import androidx.compose.runtime.MutableState
import com.assignment.one.domain.model.Product
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

const val BASE_API_STORE_URL: String = "https://fakestoreapi.com/"

class HttpClient{

    private val retrofitBuilder = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_API_STORE_URL)
        .build()
        .create(RetrofitInterface::class.java)

    private val retrofitData = retrofitBuilder.getProductData()

    fun getApiResponse() : NetResponse {
        return try {
            val response : Response<List<Product>> = retrofitData.execute()
            NetResponse.Success(response.body() ?: listOf())
        }
        catch (e: Exception){
            NetResponse.Failed(listOf())
        }
    }

    object CallBackObj: Callback<List<Product>?> {
        override fun onResponse(call: Call<List<Product>?>, response: Response<List<Product>?>){}
        override fun onFailure(call: Call<List<Product>?>, t: Throwable) {}
    }

    fun getApiResponse1() = retrofitData.enqueue(CallBackObj)
}