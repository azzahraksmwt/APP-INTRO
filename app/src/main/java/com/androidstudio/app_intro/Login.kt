package com.androidstudio.app_intro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.ImageButton
import android.widget.Toast
import com.androidstudio.app_intro.databinding.ActivityLoginBinding
import com.androidstudio.app_intro.model.ResponseLogin
import com.androidstudio.app_intro.network.RetrofitClient
import com.androidstudio.app_intro.ui.home.HomeFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Login : AppCompatActivity() {
    private var binding : ActivityLoginBinding? = null
    private var username : String = ""
    private var password : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

//        binding!!.btnsubmit.setOnClickListener{
//            username = binding!!.username.text.toString()
//            password = binding!!.password.text.toString()
//
//            when {
//                username == "" -> {
//                    binding!!.username.error = "Username tidak boleh kosong"
//                }
//                password == "" -> {
//                    binding!!.password.error = "Password tidak boleh kosong"
//                }
//                else -> {
//                    getData()
//                }
//            }
//        }
        val btnsubmit = findViewById<ImageButton>(R.id.btnsubmit)

        btnsubmit.setOnClickListener{
            val intent = Intent(this, NavigationDrawer::class.java)
            startActivity(intent)
        }
    }

//    private fun getData() {
//        val api = RetrofitClient().getInstance()
//        api.login(username, password).enqueue(object : Callback<ResponseLogin> {
//            override fun onResponse(call: Call<ResponseLogin>, response: Response<ResponseLogin>) {
//                try {
//                    if (response.isSuccessful) {
//                        val responseBody = response.body()
//                        if (responseBody?.response == true) {
//                            // Login berhasil, akses payload di sini
//                            val payload = responseBody.payload
//                            // Lakukan sesuatu dengan payload, misalnya, tampilkan pesan selamat datang
//                            val welcomeMessage = "Selamat datang, ${payload.namaPengguna}" // Ganti "nama" dengan nama properti yang sesuai di dalam payload
//                            Toast.makeText(this@Login, welcomeMessage, Toast.LENGTH_LONG).show()
//                            // Lanjutkan dengan intent ke halaman beranda atau yang sesuai
//                            val intent = Intent(this@Login, HomeFragment::class.java)
//                            startActivity(intent)
//                            finish()
//                        } else {
//                            Toast.makeText(this@Login, "Login gagal", Toast.LENGTH_LONG).show()
//                        }
//                    } else {
//                        Toast.makeText(this@Login, "Login gagal", Toast.LENGTH_LONG).show()
//                    }
//                } catch (e: Exception) {
//                    e.printStackTrace()
//                    Toast.makeText(this@Login, "Terjadi kesalahan: ${e.message}", Toast.LENGTH_LONG).show()
//                }
//            }
//
//            override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
//                try {
//                    Log.e("pesan error", "${t.message}")
//                    Toast.makeText(this@Login, "Terjadi kesalahan: ${t.message}", Toast.LENGTH_LONG).show()
//                } catch (e: Exception) {
//                    e.printStackTrace()
//                }
//            }
//        })
//    }
}