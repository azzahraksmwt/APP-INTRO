package com.androidstudio.app_intro.modeldata

data class InventoryResponse(
    val meta: Meta,
    val data: Data
) {
    data class Meta(
        val code: Int,
        val status: String,
        val message: String
    )

    data class Data(
        val inventoryItems: List<InventoryItem>
    )
}