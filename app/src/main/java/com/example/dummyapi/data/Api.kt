package com.example.dummyapi.data

import com.example.dummyapi.products.Products
import retrofit2.http.GET

interface Api {

    @GET("products")
    suspend fun getProductItems():Products


    companion object {
        val BASE_URL = "https://dummyjson.com/"
    }
}