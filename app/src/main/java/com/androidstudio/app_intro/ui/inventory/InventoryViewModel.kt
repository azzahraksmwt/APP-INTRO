package com.androidstudio.app_intro.ui.inventory

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.androidstudio.app_intro.api.ApiUtils
import com.androidstudio.app_intro.modeldata.InventoryItem
import com.androidstudio.app_intro.modeldata.InventoryResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InventoryViewModel : ViewModel() {

    val inventoryItems = MutableLiveData<List<InventoryItem>>() // Change the type here
    val errorMessage = MutableLiveData<String>()

    fun fetchInventoryItems() {
        ApiUtils.getApiService().getInventoryItems().enqueue(object : Callback<InventoryResponse> {
            override fun onResponse(call: Call<InventoryResponse>, response: Response<InventoryResponse>) {
                if (response.isSuccessful) {
                    inventoryItems.postValue(response.body()?.data?.inventoryItems)
                } else {
                    errorMessage.postValue("Error: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<InventoryResponse>, t: Throwable) {
                errorMessage.postValue("Network Error: ${t.message}")
            }
        })
    }
}
