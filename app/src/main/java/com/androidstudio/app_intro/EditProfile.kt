package com.androidstudio.app_intro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.androidstudio.app_intro.api.ApiService
import com.androidstudio.app_intro.api.ApiUtils
import com.androidstudio.app_intro.api.SecureStorage
import com.androidstudio.app_intro.modeldata.ApiResponse
import com.androidstudio.app_intro.modeldata.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditProfile : AppCompatActivity() {
    private lateinit var apiService: ApiService
    private lateinit var user: LoginResponse.Data.User
    private lateinit var nameEditText: EditText
    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var saveButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        // Inisialisasi ApiService
        apiService = ApiUtils.getApiService()

        // Mendapatkan data pengguna dari aktivitas sebelumnya (misalnya, setelah login)
        user = intent.getParcelableExtra("user")!!



        // Inisialisasi elemen-elemen UI
        nameEditText = findViewById(R.id.name)
        usernameEditText = findViewById(R.id.username)
        passwordEditText = findViewById(R.id.password)
        saveButton = findViewById(R.id.btnsave)

        // Menampilkan data pengguna dalam EditText
        nameEditText.setText(user.namaPengguna)
        usernameEditText.setText(user.username)
        passwordEditText.setText(user.password)

        // Meng-handle tombol Save
        saveButton.setOnClickListener {
            // Mengambil data dari EditText
            val newName = nameEditText.text.toString()
            val newUsername = usernameEditText.text.toString()
            val newPassword = passwordEditText.text.toString()

            // Mengupdate data pengguna
            user.namaPengguna = newName
            user.username = newUsername
            user.password = newPassword

            // Mengirim permintaan untuk mengupdate profil
            editUserProfile(user)
        }
    }

    private fun editUserProfile(user: LoginResponse.Data.User) {
        // Inisialisasi SecureStorage
        val secureStorage = SecureStorage(this)

// Mengambil token dari SecureStorage
        val token = secureStorage.getToken()


        user.let {
            apiService.editUserProfile(
                it.id, // Ganti dengan ID pengguna
                it.namaPengguna,
                it.username,
                it.password
            ).enqueue(object : Callback<LoginResponse> { // Perbaiki tipe data menjadi LoginResponse
                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    if (response.isSuccessful) {
                        // Handle jika profil berhasil diedit
                        if (response.body()?.meta?.code == 200) {
                            // Profil berhasil diubah, tampilkan pesan sukses
                            showToast("Profile updated successfully")
                        } else {
                            // Terjadi kesalahan di sisi server, tampilkan pesan kesalahan
                            showToast("Failed to update profile: ${response.body()?.meta?.message}")
                        }
                    } else {
                        // Handle jika terjadi kesalahan saat mengedit profil (kode status selain 200)
                        showToast("Failed to update profile: ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    // Handle jika terjadi kesalahan jaringan
                    showToast("Network error: ${t.message}")
                }
            })
        }

    }

    private fun showToast(message: String) {
        // Tampilkan pesan menggunakan Toast atau metode lain sesuai kebutuhan Anda
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
