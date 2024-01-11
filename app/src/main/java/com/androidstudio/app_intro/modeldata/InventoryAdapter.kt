package com.androidstudio.app_intro.modeldata

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import com.androidstudio.app_intro.R
import com.androidstudio.app_intro.ui.inventory.EditInventoryActivity

class InventoryAdapter(
    context: Context,
    private val items: MutableList<InventoryItem>,
    private val dbHelper: YourDatabaseHelperClass,
    private var onDeleteClickListener: ((position: Int, item: InventoryItem) -> Unit)? = null
) : ArrayAdapter<InventoryItem>(context, R.layout.list_item_inventory, items) {

    fun setOnDeleteClickListener(listener: ((position: Int, item: InventoryItem) -> Unit)?) {
        onDeleteClickListener = listener
    }

    fun deleteItem(position: Int) {
        if (position >= 0 && position < items.size) {
            val item = items[position]
            if (deleteInventoryItem(item.idbarang.toString())) {
                items.removeAt(position)
                notifyDataSetChanged()
            }
        }
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val rowView: View
        val viewHolder: ViewHolder

        if (convertView == null) {
            val inflater = LayoutInflater.from(context)
            rowView = inflater.inflate(R.layout.list_item_inventory, parent, false)
            viewHolder = ViewHolder()

            viewHolder.namabarangTextView = rowView.findViewById(R.id.namabarangTextView)
            viewHolder.jenisbarangTextView = rowView.findViewById(R.id.jenisbarangTextView)
            viewHolder.jumlahbarangTextView = rowView.findViewById(R.id.jumlahbarangTextView)
            viewHolder.uomTextView = rowView.findViewById(R.id.uomTextView)
            viewHolder.editButton = rowView.findViewById(R.id.edit)
            viewHolder.deleteButton = rowView.findViewById(R.id.delete)

            rowView.tag = viewHolder
        } else {
            rowView = convertView
            viewHolder = rowView.tag as ViewHolder
        }

        val item = items[position]

        viewHolder.editButton.setOnClickListener {
            navigateToEditInventoryActivity(item)
        }

        viewHolder.deleteButton.setOnClickListener {
            onDeleteClickListener?.invoke(position, item) // Memanggil onDeleteClickListener dengan posisi dan item
        }

        viewHolder.namabarangTextView.text = item.namabarang
        viewHolder.jenisbarangTextView.text = item.jenisbarang
        viewHolder.jumlahbarangTextView.text = item.jumlahbarang.toString()
        viewHolder.uomTextView.text = item.uom

        return rowView
    }

    private class ViewHolder {
        lateinit var namabarangTextView: TextView
        lateinit var jenisbarangTextView: TextView
        lateinit var jumlahbarangTextView: TextView
        lateinit var uomTextView: TextView
        lateinit var editButton: ImageButton
        lateinit var deleteButton: ImageButton
    }

    private fun navigateToEditInventoryActivity(item: InventoryItem) {
        val intent = Intent(context, EditInventoryActivity::class.java)
        intent.putExtra("inventory_item", item)
        intent.putExtra("idbarang", item.idbarang)
        context.startActivity(intent)
    }

    private fun deleteInventoryItem(idbarang: String): Boolean {
        val db = dbHelper.writableDatabase
        val args = arrayOf(idbarang)
        val result = db.delete("inventorys", "idbarang = ?", args)
        db.close()
        return result > 0
    }
}
