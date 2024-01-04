package com.androidstudio.app_intro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import com.androidstudio.app_intro.model.InventoryModel
import com.androidstudio.app_intro.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private val api by lazy {RetrofitClient().apiclient}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnnext = findViewById<ImageButton>(R.id.btnnext)

        btnnext.setOnClickListener{
            val intent = Intent(this,Login::class.java)
            startActivity(intent)
        }

//        api.data().enqueue(object : Callback<InventoryModel>{
//            override fun onResponse(
//                call: Call<InventoryModel>,
//                response: Response<InventoryModel>
//            ) {
//                if (response.isSuccessful){
//                    val listData = response.body()!!.inventoryItems
//                    listData.forEach {
//                        Log.e("MainActivity", "namabarang ${it.namabarang}")
//                    }
////                    Log.e("MainActitvity", response.toString())
//                }
//            }
//
//            override fun onFailure(call: Call<InventoryModel>, t: Throwable) {
//                Log.e("MainActivity", t.toString())
//            }
//
//        })
    }
}