package com.androidstudio.app_intro.ui.inventory

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.androidstudio.app_intro.R
import com.androidstudio.app_intro.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.androidstudio.app_intro.model.InventoryModel
import android.util.Log
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.RecyclerView
import com.androidstudio.app_intro.listinventory.NoteAdapter
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import android.widget.ListView
import android.widget.Toast


class InventoryFragment : Fragment() {

    private val api by lazy {RetrofitClient().apiclient}
//    private lateinit var noteAdapter: NoteAdapter
    private lateinit var listNote: ListView
    private lateinit var noteAdapter: ArrayAdapter<InventoryModel.Data> // Ganti dengan ArrayAdapter


    companion object {
        fun newInstance() = InventoryFragment()
    }

    private lateinit var viewModel: InventoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_inventory, container, false)
        setupList(view) // Panggil setupList dengan view yang telah diinflate
        return view
//        return inflater.inflate(R.layout.fragment_inventory, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(InventoryViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onStart() {
        super.onStart()
        getInventory()
    }

//    private fun setupList(view: View){
//        listNote = view.findViewById(R.id.listInventory)
//        noteAdapter = NoteAdapter(arrayListOf())
//        listNote.adapter = noteAdapter
//    }

    private fun setupList(view: View) {
        listNote = view.findViewById(R.id.listInventory)
        noteAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, arrayListOf()) // Ganti dengan ArrayAdapter
        listNote.adapter = noteAdapter
    }

    private fun getInventory(){
        api.data().enqueue(object : Callback<InventoryModel>{
            override fun onResponse(
                call: Call<InventoryModel>,
                response: Response<InventoryModel>
            ) {

                if (response.isSuccessful) {
                    val listData = response.body()?.inventoryItems
                    if (listData.isNullOrEmpty()) {
                        // Tampilkan pesan kesalahan kepada pengguna
                        Toast.makeText(context, "Tidak ada data yang tersedia saat ini.", Toast.LENGTH_SHORT).show()
                    } else {
                        // Array tidak kosong, proses data seperti biasa
                        processData(listData)
                    }
//                    val listData = response.body()?.inventoryItems
//                    Log.d("InventoryFragment", "Data received: $listData")
//                    activity?.runOnUiThread {
////                        noteAdapter.setData(listData)
//                        processData(listData)
//                    }
                } else {
                    Log.e("InventoryFragment", "Response not successful: ${response.code()}")
                    Log.e("InventoryFragment", "Error body: ${response.errorBody()?.string()}")
                }

//                if (response.isSuccessful) {
//                    val responseData = response.body()
//                    if (responseData != null) {
//                        val listData = responseData.inventoryItems
//                        noteAdapter.setData(listData)
//                    } else {
//                        // Tangani kasus ketika body null
//                        Log.e("InventoryFragment", "Response body is null")
//                    }
//                } else {
//                    // Tangani kesalahan status kode HTTP
//                    Log.e("InventoryFragment", "HTTP error: ${response.code()}")
//                }

//                if (response.isSuccessful) {
//                    val listData = response.body()?.inventoryItems
//                    Log.d("InventoryFragment", "Data received: $listData")
//                    noteAdapter.setData(listData)
//                } else {
//                    Log.e("InventoryFragment", "Response not successful: ${response.code()}")
//                }

//                if (response.isSuccessful){
//                    val listData = response.body()!!.inventoryItems
//                    noteAdapter.setData(listData)
////                    listData.forEach {
////                        Log.e("InventoryFragment", "namabarang ${it.namabarang}")
////                    }
////                    Log.e("MainActitvity", response.toString())
//                }
            }

//            private fun processData(listData: List<InventoryModel.Data>?) {
//                if (listData != null) {
//                    noteAdapter.setData(listData)
//                    noteAdapter.notifyDataSetChanged() // Penting untuk memberi tahu RecyclerView bahwa dataset telah berubah
//                } else {
//                    Log.e("InventoryFragment", "Data is null")
//                }
//            }

            private fun processData(listData: List<InventoryModel.Data>?) {
                if (listData != null) {
                    noteAdapter.clear()
                    noteAdapter.addAll(listData)
                    noteAdapter.notifyDataSetChanged() // Penting untuk memberi tahu ListView bahwa dataset telah berubah
                } else {
                    Log.e("InventoryFragment", "Data is null")
                }
            }


            override fun onFailure(call: Call<InventoryModel>, t: Throwable) {
                Log.e("InventoryFragment", "Request failed: ${t.message}")
//                Log.e("InventoryFragment", t.toString())
            }

        })
    }

}