package com.androidstudio.app_intro.ui.inventory

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.androidstudio.app_intro.R
import com.androidstudio.app_intro.api.ApiUtils
import com.androidstudio.app_intro.modeldata.ApiResponse
import com.androidstudio.app_intro.modeldata.InventoryAdapter
import com.androidstudio.app_intro.modeldata.InventoryItem
import com.androidstudio.app_intro.modeldata.InventoryResponse
import com.androidstudio.app_intro.modeldata.YourDatabaseHelperClass
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InventoryFragment : Fragment() {

    private lateinit var listView: ListView
    private lateinit var adapter: InventoryAdapter
    private lateinit var viewModel: InventoryViewModel
    private lateinit var dbHelper: YourDatabaseHelperClass

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate layout for this fragment
        val view = inflater.inflate(R.layout.fragment_inventory, container, false)

        dbHelper = YourDatabaseHelperClass(requireContext())

        listView = view.findViewById(R.id.listView)
        adapter = InventoryAdapter(requireContext(), mutableListOf(), dbHelper)
        listView.adapter = adapter

        // Initialize Button and set click listener
        val btnAdd: Button = view.findViewById(R.id.btnadd)
        btnAdd.setOnClickListener {
            navigateToAddInventoryActivity()
        }
        return view
    }

    override fun onResume() {
        super.onResume()
        fetchInventoryItems()
    }

    private fun navigateToAddInventoryActivity() {
        val intent = Intent(activity, AddInventoryActivity::class.java)
        startActivity(intent)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[InventoryViewModel::class.java]
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.inventoryItems.observe(viewLifecycleOwner) { items ->
            adapter.clear()
            // Make sure items are of the correct type expected by adapter.addAll()
            adapter.addAll(items)
            adapter.notifyDataSetChanged()
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) { message ->
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        }

        listView.setOnItemClickListener { _, _, position, _ ->
            val selectedItem = adapter.getItem(position)
            if (selectedItem != null) {
                // Handle item click or navigate to details
            }
        }

        adapter.setOnDeleteClickListener { position, item ->
            // Show the delete confirmation dialog
            navigateToDeleteConfirmationDialog(item, position)
        }


        viewModel.fetchInventoryItems()
    }

    private fun fetchInventoryItems() {
        val apiService = ApiUtils.getApiService()
        apiService.getInventoryItems().enqueue(object : Callback<InventoryResponse> {
            override fun onResponse(call: Call<InventoryResponse>, response: Response<InventoryResponse>) {
                if (response.isSuccessful) {
                    response.body()?.data?.inventoryItems?.let { items ->
                        adapter.clear()
                        adapter.addAll(items)
                        adapter.notifyDataSetChanged()
                    }
                }
            }

            override fun onFailure(call: Call<InventoryResponse>, t: Throwable) {
                Toast.makeText(context, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    // Add this function to handle item deletion
    private fun deleteInventoryItem(idbarang: Int, position: Int) {
        val apiService = ApiUtils.getApiService()
        apiService.deleteInventoryItem(idbarang).enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    // Delete the item from the local list
                    adapter.remove(adapter.getItem(position))

                    // Show a toast to indicate success
                    Toast.makeText(context, "Item dihapus", Toast.LENGTH_SHORT).show()

                    // Refresh InventoryFragment to display the latest data
                    refreshInventoryFragment()
                } else {
                    // Handle API error
                    Log.d("InventoryFragment", "Delete failed with response: ${response.errorBody()?.string()}")
                    Toast.makeText(context, "Gagal menghapus item", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                // Handle network or other failures
                Log.e("InventoryFragment", "Delete failed", t)
                Toast.makeText(context, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun refreshInventoryFragment() {
        // Re-fetch the data and update the fragment
        viewModel.fetchInventoryItems()
    }

    // Add this function to show the delete confirmation dialog
    private fun navigateToDeleteConfirmationDialog(item: InventoryItem, position: Int) {
        val alertDialogBuilder = AlertDialog.Builder(requireContext())
        alertDialogBuilder.setTitle("Konfirmasi Hapus")
        alertDialogBuilder.setMessage("Apakah Anda yakin ingin menghapus item ini?")
        alertDialogBuilder.setPositiveButton("Ya") { _, _ ->
            deleteInventoryItem(item.idbarang, position)
        }
        alertDialogBuilder.setNegativeButton("Tidak") { dialog, _ ->
            dialog.dismiss()
        }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

}
