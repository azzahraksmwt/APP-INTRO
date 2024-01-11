package com.androidstudio.app_intro.ui.inventory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.androidstudio.app_intro.R
import com.androidstudio.app_intro.api.ApiService
import com.androidstudio.app_intro.api.ApiUtils
import com.androidstudio.app_intro.modeldata.ApiResponse
import com.androidstudio.app_intro.modeldata.InventoryItem
import com.androidstudio.app_intro.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditInventoryActivity : AppCompatActivity() {

    private lateinit var namabarangEditText: EditText
    private lateinit var jenisbarangEditText: EditText
    private lateinit var jumlahbarangEditText: EditText
    private lateinit var uomEditText: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_inventory)

        val idbarang = intent.getIntExtra("idbarang", -1) // Default to -1 if not found

        if (idbarang == -1) {
            Toast.makeText(this, "Error: Item ID is missing", Toast.LENGTH_SHORT).show()
            return
        }

        val updateButton: Button = findViewById(R.id.btnsave)
        updateButton.setOnClickListener {
            updateInventoryItem()
        }

        val btnBack: TextView = findViewById(R.id.btnBack)
        btnBack.setOnClickListener {
            // Kembali ke InventoryFragment
            onBackPressed()
        }

        // Mendapatkan data item dari Intent
        val item = intent.getParcelableExtra<InventoryItem>("inventory_item")

        // Inisialisasi komponen EditText atau komponen lainnya sesuai dengan layout Anda
        namabarangEditText = findViewById(R.id.namabarang)
        jenisbarangEditText = findViewById(R.id.jenisbarang)
        jumlahbarangEditText = findViewById(R.id.jumlahbarang)
        uomEditText = findViewById(R.id.uom)

        // Set nilai EditText sesuai dengan data item
        namabarangEditText.setText(item?.namabarang)
        jenisbarangEditText.setText(item?.jenisbarang)
        jumlahbarangEditText.setText(item?.jumlahbarang.toString())
        uomEditText.setText(item?.uom)
    }

    private fun updateInventoryItem() {
        val idbarang = intent.getIntExtra("idbarang", -1) // Make sure this matches the key used in the adapter
        if (idbarang == -1) {
            Toast.makeText(this, "Error: Item ID is missing", Toast.LENGTH_SHORT).show()
            return
        }

    // Retrieve updated values from EditTexts
    val namabarang = namabarangEditText.text.toString()
    val jenisbarang = jenisbarangEditText.text.toString()
        // Convert jumlahbarang from String to Int
        val jumlahbarangString = jumlahbarangEditText.text.toString()
        Log.d("EditInventoryActivity", "jumlahbarangString: $jumlahbarangString")
        val jumlahbarang: Int = try {
            jumlahbarangString.toInt()
        } catch (e: NumberFormatException) {
            Toast.makeText(this, "Error: jumlahbarang is not a valid number", Toast.LENGTH_SHORT).show()
            return
        }
        Log.d("EditInventoryActivity", "jumlahbarang: $jumlahbarang")

        val uom = uomEditText.text.toString()

    // Call the API to update the inventory item
    val apiService = ApiUtils.getApiService()
    apiService.updateInventory(idbarang, namabarang, jenisbarang, jumlahbarang, uom).enqueue(object :
        Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    Log.d("EditInventoryActivity", "Update successful")
                    Toast.makeText(this@EditInventoryActivity, "Data berhasil diubah", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Log.d("EditInventoryActivity", "Update failed with response: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                Log.e("EditInventoryActivity", "Update failed", t)
                Toast.makeText(this@EditInventoryActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

}