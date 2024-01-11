package com.androidstudio.app_intro.api

import android.content.Context
import android.content.SharedPreferences
import com.androidstudio.app_intro.modeldata.LoginResponse
import com.google.gson.Gson

class SecureStorage(private val context: Context) {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("MyApp", Context.MODE_PRIVATE)

    fun saveToken(token: String) {
        val editor = sharedPreferences.edit()
        editor.putString("token", encryptToken(token))
        editor.apply()
    }

    private fun encryptToken(token: String): String {
        // Implementasi enkripsi menggunakan Android Keystore atau metode enkripsi yang Anda pilih
        // Misalnya, Anda dapat menggunakan enkripsi AES atau RSA
        // ...
        return token // Ganti dengan token yang sudah dienkripsi
    }

    fun getToken(): String {
        return decryptToken(sharedPreferences.getString("token", null))
    }

    private fun decryptToken(encryptedToken: String?): String {
        // Implementasi dekripsi menggunakan keystore atau metode dekripsi yang Anda pilih
        // ...
        return encryptedToken ?: "" // Ganti dengan token yang sudah didekripsi
    }

    fun clearToken() {
        val editor = sharedPreferences.edit()
        editor.remove("token")
        editor.apply()
    }

    fun saveUserData(userData: LoginResponse.Data.User) {
        val userDataString = Gson().toJson(userData)
        val editor = sharedPreferences.edit()
        editor.putString("userData", userDataString)
        editor.apply()
    }

    fun getUserData(): LoginResponse.Data.User? {
        val userDataString = sharedPreferences.getString("userData", null)
        return if (userDataString != null) {
            Gson().fromJson(userDataString, LoginResponse.Data.User::class.java)
        } else {
            null
        }
    }

    // Fungsi lainnya sesuai kebutuhan Anda

    fun saveUserId(idPengguna: String) {
        val editor = sharedPreferences.edit()
        editor.putString("idPengguna", idPengguna)
        editor.apply()
    }

    fun getUserId(): String? {
        return sharedPreferences.getString("idPengguna", null)
    }
}
