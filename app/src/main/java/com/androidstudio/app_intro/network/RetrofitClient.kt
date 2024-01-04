package com.androidstudio.app_intro.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
//    private fun getRetrofitClient():Retrofit{
//        return Retrofit.Builder()
//            .baseUrl("http://192.168.43.48:8000/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//    }
//
//    fun getInstance(): ApiClient {
//        return getRetrofitClient().create(ApiClient::class.java)
//    }

    val apiclient: ApiClient
        get() {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            val client = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl("http://192.168.43.48:8000/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return  retrofit.create(ApiClient::class.java)
        }
}