package com.androidstudio.app_intro.api

import com.androidstudio.app_intro.modeldata.LoginResponse
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonParser
import com.google.gson.JsonSyntaxException

class ApiResponseHandler {
    private val gson = Gson()

    fun handleApiResponse(jsonData: String): List<LoginResponse.Data.User> {
        try {
            // Coba parse sebagai objek array
            return gson.fromJson(jsonData, Array<LoginResponse.Data.User>::class.java).toList()
        } catch (e: JsonSyntaxException) {
            if (e.message == "Expected BEGIN_OBJECT but was BEGIN_ARRAY") {
                // Jika JSON adalah array, parse sebagai array
                val jsonArray = JsonParser.parseString(jsonData).asJsonArray
                val users = mutableListOf<LoginResponse.Data.User>()

                for (element in jsonArray) {
                    val user = gson.fromJson(element, LoginResponse.Data.User::class.java)
                    users.add(user)
                }
                return users
            } else {
                // Handle error JSON parsing lainnya
                throw e
            }
        }
    }
}
