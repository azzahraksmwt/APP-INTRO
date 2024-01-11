package com.androidstudio.app_intro.api

import com.androidstudio.app_intro.modeldata.AddInventoryResponse
import com.androidstudio.app_intro.modeldata.ApiResponse
import com.androidstudio.app_intro.modeldata.InventoryResponse
import com.androidstudio.app_intro.modeldata.LoginResponse
import com.androidstudio.app_intro.modeldata.UsageData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @FormUrlEncoded
    @POST("api/login")
    fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): Call<LoginResponse>

    @GET("api/inventory")
    fun getInventoryItems(): Call<InventoryResponse>

    @FormUrlEncoded
    @POST("api/inventory/store")
    fun addInventory(
        @Field("namabarang") namabarang: String,
        @Field("jenisbarang") jenisbarang: String,
        @Field("jumlahbarang") jumlahbarang: Int,
        @Field("uom") uom: String
    ): Call<AddInventoryResponse>

    @POST("api/usage/store")
    fun addUsage(@Body usageData: UsageData): Call<ApiResponse>

//    @DELETE("api/inventory/{id}")
//    fun deleteInventoryItem(@Path("id") id: String): Call<ApiResponse>

    @DELETE("api/inventory/delete/{idbarang}")
    fun deleteInventoryItem(@Path("idbarang") idbarang: Int): Call<ApiResponse>

    @FormUrlEncoded
    @POST("api/inventory/update/{idbarang}")
    fun updateInventory(
        @Path("idbarang") idbarang: Int,
        @Field("namabarang") namabarang: String,
        @Field("jenisbarang") jenisbarang: String,
        @Field("jumlahbarang") jumlahbarang: Int,
        @Field("uom") uom: String
    ): Call<ApiResponse>

    @FormUrlEncoded
    @POST("api/editprofile")
    fun editUserProfile(
        @Field("id") id: Int,
        @Field("namaPengguna") namaPengguna: String,
        @Field("username") username: String,
        @Field("password") password: String
    ): Call<LoginResponse>

}