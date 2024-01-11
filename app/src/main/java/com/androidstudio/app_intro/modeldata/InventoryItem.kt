package com.androidstudio.app_intro.modeldata

import android.os.Parcel
import android.os.Parcelable

data class InventoryItem(
    val idbarang: Int,
    val idtype: String?,
    val namabarang: String?,
    val jenisbarang: String?,
    val jumlahbarang: Int,
    val satuan: String?,
    val uom: String?,
    val modifiedbydate: String?,
    val idPengguna: String?,
    val created_at: String?,
    val updated_at: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(idbarang)
        parcel.writeString(idtype)
        parcel.writeString(namabarang)
        parcel.writeString(jenisbarang)
        parcel.writeInt(jumlahbarang)
        parcel.writeString(satuan)
        parcel.writeString(uom)
        parcel.writeString(modifiedbydate)
        parcel.writeString(idPengguna)
        parcel.writeString(created_at)
        parcel.writeString(updated_at)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<InventoryItem> {
        override fun createFromParcel(parcel: Parcel): InventoryItem {
            return InventoryItem(parcel)
        }

        override fun newArray(size: Int): Array<InventoryItem?> {
            return arrayOfNulls(size)
        }
    }
}


