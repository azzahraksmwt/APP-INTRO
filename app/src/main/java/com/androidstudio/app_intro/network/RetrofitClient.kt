package com.androidstudio.app_intro.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    private fun getRetrofitClient():Retrofit{
        return Retrofit.Builder()
            .baseUrl("http://192.168.43.48:8000/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getInstance(): ApiClient {
        return getRetrofitClient().create(ApiClient::class.java)
    }
}