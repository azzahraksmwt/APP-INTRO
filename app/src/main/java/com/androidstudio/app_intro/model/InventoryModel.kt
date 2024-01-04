package com.androidstudio.app_intro.model

import java.sql.Date
import java.sql.Timestamp

data class InventoryModel(
    val inventoryItems: List<Data> = emptyList()
){
    data class Data (
        val idbarang: Int?,
        val idtype: String?,
        val namabarang: String?,
        val jenisbarang: String?,
        val jumlahbarang: Int?,
        val satuan: String?,
        val uom: String?,
        val modifiedbydate: Date?,
        val idPengguna: String?,
        val created_at: Date?,
        val updated_at: Date?
    )
}
