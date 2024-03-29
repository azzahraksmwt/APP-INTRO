package com.androidstudio.app_intro.network

import com.androidstudio.app_intro.model.InventoryModel
import com.androidstudio.app_intro.model.ResponseLogin
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiClient {
    @FormUrlEncoded
    @POST("api/login")
    fun login(
        @Field("username") username : String,
        @Field("password") password : String
    ): Call<ResponseLogin>

    @GET("api/inventory")
    fun data() :Call<InventoryModel>
}