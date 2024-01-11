package com.androidstudio.app_intro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.androidstudio.app_intro.api.ApiUtils
import com.androidstudio.app_intro.api.SecureStorage
import com.androidstudio.app_intro.databinding.ActivityLoginBinding
import com.androidstudio.app_intro.model.ResponseLogin
import com.androidstudio.app_intro.network.RetrofitClient
import com.androidstudio.app_intro.ui.home.HomeFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Login : AppCompatActivity() {
    private var binding : ActivityLoginBinding? = null
//    private var username : String = ""
//    private var password : String = ""

    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: ImageButton

    private val apiService = ApiUtils.getApiService()
    private val secureStorage by lazy { SecureStorage(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        val btnBack = findViewById<ImageButton>(R.id.btnback)
        btnBack.setOnClickListener {
            finish()
        }

        // Inisialisasi komponen view
        usernameEditText = findViewById(R.id.username)
        passwordEditText = findViewById(R.id.password)
        loginButton = findViewById(R.id.btnlogin)


        // Set listener untuk tombol login
        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()
            if (validateInput(username, password)) {
                performLogin(username, password)
            }
        }

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
//        val btnsubmit = findViewById<ImageButton>(R.id.btnsubmit)
//
//        btnsubmit.setOnClickListener{
//            val intent = Intent(this, NavigationDrawer::class.java)
//            startActivity(intent)
//        }
    }

    private fun validateInput(username: String, password: String): Boolean {
        var isValid = true
        if (username.isEmpty()) {
            usernameEditText.error = "Username tidak boleh kosong"
            isValid = false
        }
        if (password.isEmpty()) {
            passwordEditText.error = "Password tidak boleh kosong"
            isValid = false
        }
        return isValid
    }

    private fun performLogin(username: String, password: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = apiService.login(username, password).execute()
                if (response.isSuccessful && response.body()?.meta?.status == "success") {
                    response.body()?.data?.access_token?.let { token ->
                        secureStorage.saveToken(token)
                        runOnUiThread {
                            Toast.makeText(
                                this@Login,
                                "Login successful!",
                                Toast.LENGTH_SHORT
                            ).show()
                            navigateToMenuActivity()
                        }
                    }
                    // Setelah Anda mendapatkan respons login dan data pengguna dari server
                    val userData = response.body()?.data?.user

                    // Simpan data pengguna ke SharedPreferences
                    if (userData != null) {
                        secureStorage.saveUserData(userData)
                    }
                } else {
                    showError("Login failed: ${response.body()?.meta?.message}")
                }
            } catch (e: Exception) {
                showError("Login error: ${e.message}")
            }
        }
    }

    private fun navigateToMenuActivity() {
        val intent = Intent(this, NavigationDrawer::class.java)
        startActivity(intent)
        finish()
    }

    private fun showError(message: String) {
        runOnUiThread {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
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