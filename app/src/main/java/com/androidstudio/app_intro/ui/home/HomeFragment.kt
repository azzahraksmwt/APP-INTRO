package com.androidstudio.app_intro.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.androidstudio.app_intro.R
import com.androidstudio.app_intro.api.ApiService
import com.androidstudio.app_intro.api.ApiUtils
import com.androidstudio.app_intro.api.SecureStorage
import com.androidstudio.app_intro.databinding.FragmentHomeBinding
import com.androidstudio.app_intro.modeldata.InventoryItem
import com.androidstudio.app_intro.modeldata.InventoryResponse
import com.androidstudio.app_intro.modeldata.ListGoodsAdapter
import com.androidstudio.app_intro.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var listGoodsAdapter: ListGoodsAdapter
    private lateinit var secureStorage: SecureStorage

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        secureStorage = SecureStorage(requireContext())
        val userData = secureStorage.getUserData()
        binding.nama.text = userData?.namaPengguna ?: "Nama Pengguna Tidak Tersedia"

        setupListView()
        fetchInventoryItems()

        return binding.root
    }

    private fun setupListView() {
        listGoodsAdapter = ListGoodsAdapter(requireContext(), mutableListOf())
        binding.listgoodsData.adapter = listGoodsAdapter
    }


    private fun fetchInventoryItems() {
        ApiUtils.getApiService().getInventoryItems().enqueue(object : Callback<InventoryResponse> {
            override fun onResponse(call: Call<InventoryResponse>, response: Response<InventoryResponse>) {
                if (response.isSuccessful) {
                    response.body()?.data?.inventoryItems?.let { items ->
                        listGoodsAdapter.clear()
                        listGoodsAdapter.addAll(items)
                        listGoodsAdapter.notifyDataSetChanged()
                    }
                }
            }


            override fun onFailure(call: Call<InventoryResponse>, t: Throwable) {
                // Handle failure
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
