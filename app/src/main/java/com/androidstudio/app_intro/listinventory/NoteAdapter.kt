package com.androidstudio.app_intro.listinventory

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import com.androidstudio.app_intro.R
import androidx.recyclerview.widget.RecyclerView
import com.androidstudio.app_intro.model.InventoryModel
import java.text.SimpleDateFormat
import java.util.Locale

class NoteAdapter(
    val inventoryItems: ArrayList<InventoryModel.Data>
): RecyclerView.Adapter<NoteAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.list_inventory, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = inventoryItems[position]
        val idText = "ID Barang: ${data.idbarang}, ID Type: ${data.idtype}"
        holder.id.text = idText
        holder.nama.text = data.namabarang
        holder.uom.text = data.uom
        holder.type.text = data.jenisbarang
        val qtySatuanText = "Jumlah: ${data.jumlahbarang} ${data.satuan}"
        holder.qtySatuan.text = qtySatuanText
//        holder.qty.text = data.jumlahbarang
//        holder.satuan.text = data.satuan
//        holder.date.text = data.modifiedbydate
        val dateFormatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val formattedDate = data.modifiedbydate?.let { dateFormatter.format(it) } ?: ""
        holder.date.text = formattedDate
        holder.lecturer.text = data.idPengguna
    }

    override fun getItemCount() = inventoryItems.size

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val id = view.findViewById<TextView>(R.id.idinventory)
        val nama = view.findViewById<TextView>(R.id.namainventory)
        val uom = view.findViewById<TextView>(R.id.uominventory)
        val type = view.findViewById<TextView>(R.id.typeinventory)
//        val qty = view.findViewById<TextView>(R.id.qtyinventory)
//        val satuan = view.findViewById<TextView>(R.id.satuaninventory)
        val qtySatuan = view.findViewById<TextView>(R.id.qtyinventory)
        val date = view.findViewById<TextView>(R.id.dateinventory)
        val lecturer = view.findViewById<TextView>(R.id.lecturerinventory)
        val edit = view.findViewById<ImageButton>(R.id.editinventory)
        val delete = view.findViewById<ImageButton>(R.id.deleteinventory)
    }

    public fun setData(data: List<InventoryModel.Data>?){
        if (data != null) {
            inventoryItems.clear()
            inventoryItems.addAll(data)
            notifyDataSetChanged()
        }
    }
}
