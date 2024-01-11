package com.androidstudio.app_intro.ui.inventory

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.androidstudio.app_intro.api.ApiUtils
import com.androidstudio.app_intro.databinding.ActivityAddInventoryBinding
import com.androidstudio.app_intro.modeldata.AddInventoryResponse
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddInventoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddInventoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddInventoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.btnadd.setOnClickListener {
            addInventory()
        }
    }

    private fun addInventory() {
        val namabarang = binding.namabarang.text.toString().trim()
        val jenisbarang = binding.jenisbarang.text.toString().trim()
        val jumlahbarangText = binding.jumlahbarang.text.toString().trim() // Ambil sebagai String
        val uom = binding.uom.text.toString().trim()

        if (namabarang.isEmpty() || jenisbarang.isEmpty() || jumlahbarangText.isEmpty() || uom.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            return
        }

        // Parse jumlahbarangText menjadi Int
        val jumlahbarang = jumlahbarangText.toIntOrNull()
        if (jumlahbarang == null) {
            Toast.makeText(this, "Invalid quantity", Toast.LENGTH_SHORT).show()
            return
        }

        val apiService = ApiUtils.getApiService()
        val call = apiService.addInventory(namabarang, jenisbarang, jumlahbarang, uom)

        call.enqueue(object : Callback<AddInventoryResponse> {
            override fun onResponse(call: Call<AddInventoryResponse>, response: Response<AddInventoryResponse>) {
                if (response.isSuccessful) {
                    val inventory = response.body()?.data?.inventory
                    if (inventory != null) {
                        // Arahkan pengguna ke ListData
                        // ...

                        // Set the result for the calling activity
                        val successIntent = Intent()
                        successIntent.putExtra("inventoryAdded", true)
                        setResult(Activity.RESULT_OK, successIntent)

                        // Finish this activity
                        finish()
                    }
                } else {
                    // Handle kesalahan jika permintaan tidak berhasil
                    val errorBody = response.errorBody()?.string()
                    val headers = response.headers().toString()
                    Log.e("AddInventoryActivity", "Failed with status code: ${response.code()}")
                    Log.e("AddInventoryActivity", "Error Body: $errorBody")
                    Log.e("AddInventoryActivity", "Headers: $headers")
                    Toast.makeText(this@AddInventoryActivity, "Failed to add inventory", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<AddInventoryResponse>, t: Throwable) {

                // Handle kesalahan jaringan atau permintaan
                Log.e("AddInventoryActivity", "Network error", t)
                Toast.makeText(this@AddInventoryActivity, "Network error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
