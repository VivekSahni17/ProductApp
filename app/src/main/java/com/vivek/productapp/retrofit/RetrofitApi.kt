package com.vivek.productapp.retrofit

import com.vivek.productapp.ProductList

import retrofit2.Call
import retrofit2.http.GET

import retrofit2.http.QueryMap

interface RetrofitApi {

    @GET("products")
//    fun getProductList(@QueryMap mp: HashMap<String, String>): Call<ProductList>
    fun getProductList(): Call<ProductList>





}