package com.example.recipeapp2.Adapters.repository

import com.example.recipeapp2.Entities.CategoriesResponse
import com.example.recipeapp2.Entities.Product
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


private val retrofit = Retrofit.Builder().baseUrl("https://www.themealdb.com/api/json/v1/1/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()
private val retrofit2 = Retrofit.Builder().baseUrl("https://fakestoreapi.com/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

    val recipeService = retrofit.create(ApiService::class.java)
    val productService = retrofit2.create(ApiService2::class.java)


interface ApiService {
    @GET("categories.php")
    suspend fun getCategories(): CategoriesResponse
}

interface ApiService2{
    @GET("products")
    suspend fun getProducts():List<Product>
}