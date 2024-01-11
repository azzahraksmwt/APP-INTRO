package com.androidstudio.app_intro.modeldata
// ApiResponse.kt
data class ApiResponse(
    val meta: Meta,
    val data: Data
)

data class Meta(
    val code: Int,
    val status: String,
    val message: String
)

data class Data(
    val usage: Usage
)

data class Usage(
    val idbarang: String,
    val quantity_pinjam: String,
    val idPengguna: String,
    val tanggal_pinjam: String,
    val idMatakuliah: String,
    val updated_at: String,
    val created_at: String,
    val idpb: Int,
    val inventory: Inventory
)

data class Inventory(
    val idbarang: Int,
    val idtype: String,
    val namabarang: String,
    val jenisbarang: String,
    val jumlahbarang: Int,
    val satuan: String,
    val uom: String,
    val modifiedbydate: String,
    val idPengguna: String,
    val created_at: String,
    val updated_at: String
)

