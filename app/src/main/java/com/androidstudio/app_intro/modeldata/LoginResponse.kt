package com.androidstudio.app_intro.modeldata
data class LoginResponse(
    val meta: Meta,
    val data: Data
) {
    data class Meta(
        val code: Int,
        val status: String,
        val message: String
    )

    data class Data(
        val access_token: String,
        val token_type: String,
        val user: User
    ) {
        data class User(
            val id: Int,
            val idPengguna: String,
            var namaPengguna: String,
            val email: String?, // nullable karena "null" dalam JSON
            val email_verified_at: String?, // nullable
            val kelas: String?, // nullable
            val nohp: String?, // nullable
            val angkatan: Int?, // nullable
            var username: String,
            var password: String, // Catatan: sebaiknya password tidak dikirimkan ke client
            val admin: String?, // nullable
            val role: String,
            val created_at: String,
            val updated_at: String
        )
    }
}