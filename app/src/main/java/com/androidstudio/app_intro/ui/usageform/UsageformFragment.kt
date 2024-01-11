package com.androidstudio.app_intro.ui.usageform

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.androidstudio.app_intro.R
import com.androidstudio.app_intro.api.ApiService
import com.androidstudio.app_intro.api.ApiUtils
import com.androidstudio.app_intro.api.SecureStorage
import com.androidstudio.app_intro.modeldata.ApiResponse
import com.androidstudio.app_intro.modeldata.InventoryItem
import com.androidstudio.app_intro.modeldata.InventoryResponse
import com.androidstudio.app_intro.modeldata.UsageData
import com.google.android.material.textfield.TextInputEditText
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Calendar

class UsageformFragment : Fragment() {

    private lateinit var spinnerNamabarang: Spinner
    private lateinit var apiService: ApiService
    private lateinit var inventoryItems: List<InventoryItem>
    private lateinit var qtyAvailableEditText: TextView
    private lateinit var qtyBorrowEditText: EditText
    private lateinit var datePickerEditText: TextInputEditText
    private lateinit var selectedInventoryItem: InventoryItem
    private var jsonDataToSend: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_usageform, container, false)
        spinnerNamabarang = view.findViewById(R.id.spinnerNamabarang)
        qtyAvailableEditText = view.findViewById(R.id.qtyavailable)
        qtyBorrowEditText = view.findViewById(R.id.qtyborrow)
        datePickerEditText = view.findViewById(R.id.datePickerEditText)

        datePickerEditText.setOnClickListener {
            showDatePickerDialog()
        }

        // Initialize apiService
        apiService = ApiUtils.getApiService()

        fetchInventoryData()

        val addButton = view.findViewById<Button>(R.id.btnadd)
        addButton.setOnClickListener {
            onAddButtonClicked()
        }

        return view
    }

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(requireContext(), { _, year, monthOfYear, dayOfMonth ->
            // Handling the selected date
            val selectedDate = "$year-${monthOfYear + 1}-$dayOfMonth"
            datePickerEditText.setText(selectedDate)
        }, year, month, dayOfMonth)

        datePickerDialog.show()
    }

    private fun fetchInventoryData() {
        apiService.getInventoryItems().enqueue(object : Callback<InventoryResponse> {
            override fun onResponse(call: Call<InventoryResponse>, response: Response<InventoryResponse>) {
                if (response.isSuccessful) {
                    inventoryItems = response.body()?.data?.inventoryItems ?: emptyList()
                    setupSpinner(inventoryItems)
                } else {
                    // Handle error response
                    val errorMessage = "Error: ${response.code()} ${response.message()}"
                    Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<InventoryResponse>, t: Throwable) {
                // Handle network failure
                val failureMessage = "Network Error: ${t.localizedMessage}"
                Toast.makeText(requireContext(), failureMessage, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun setupSpinner(inventoryItems: List<InventoryItem>) {
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, inventoryItems.map { it.namabarang })
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerNamabarang.adapter = adapter

        spinnerNamabarang.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                if (position >= 0 && position < inventoryItems.size) {
                    selectedInventoryItem = inventoryItems[position]
                    qtyAvailableEditText.text = selectedInventoryItem.jumlahbarang.toString()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }

    private fun onAddButtonClicked() {
        // Validasi input dan kumpulkan data yang dibutuhkan
        val selectedInventoryItem = inventoryItems[spinnerNamabarang.selectedItemPosition]
        val idbarang = selectedInventoryItem.idbarang.toString()
        val qtyBorrowed = qtyBorrowEditText.text.toString()
        val date = datePickerEditText.text.toString()

        // Kirim data pemakaian ke server
        val usageData = UsageData(
            idbarang,
            qtyBorrowed,
            getUserIdFromStorage(), // Mengambil idPengguna dari penyimpanan
            date,
            "mk-1" // Ganti dengan id mata kuliah yang sesuai
        )

        // Panggil metode untuk mengirim data ke server dan kirim objek usageData
        sendUsageDataToServer(usageData)
    }

    private fun sendUsageDataToServer(usageData: UsageData) {
        apiService.addUsage(usageData).enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    val message = response.body()?.meta?.message
                    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
                    // Lakukan apa yang diperlukan setelah berhasil
                } else {
                    val errorMessage = "Error: ${response.code()} ${response.message()}"
                    Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                val failureMessage = "Network Error: ${t.localizedMessage}"
                Toast.makeText(requireContext(), failureMessage, Toast.LENGTH_LONG).show()
            }
        })
    }


    private fun getUserIdFromStorage(): String {
        val secureStorage = SecureStorage(requireContext()) // Create an instance of your SecureStorage class
        return secureStorage.getUserId() ?: ""
    }
}
