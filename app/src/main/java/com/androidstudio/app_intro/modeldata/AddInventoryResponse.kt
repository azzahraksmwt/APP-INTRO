package com.androidstudio.app_intro.modeldata

import com.google.gson.annotations.SerializedName

data class AddInventoryResponse(
    @SerializedName("meta") val meta: Meta,
    @SerializedName("data") val data: Data
) {
    data class Meta(
        @SerializedName("code") val code: Int,
        @SerializedName("status") val status: String,
        @SerializedName("message") val message: String
    )

    data class Data(
        @SerializedName("inventory") val inventory: Inventory
    )

    data class Inventory(
        @SerializedName("updated_at") val updated_at: String,
        @SerializedName("created_at") val created_at: String,
        @SerializedName("idbarang") val idbarang: Int,
        @SerializedName("namabarang") val namabarang: String,
        @SerializedName("jenisbarang") val jenisbarang: String,
        @SerializedName("jumlahbarang") val jumlahbarang: Int,
        @SerializedName("satuan") val satuan: String,
        @SerializedName("uom") val uom: String
    )

}
