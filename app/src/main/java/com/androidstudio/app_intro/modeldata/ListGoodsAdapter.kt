package com.androidstudio.app_intro.modeldata

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.androidstudio.app_intro.R

class ListGoodsAdapter (context: Context, private val items: List<InventoryItem>) : ArrayAdapter<InventoryItem>(context, R.layout.list_goods, items) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val viewHolder: ViewHolder
        val view: View

        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.list_goods, parent, false)
            viewHolder = ViewHolder()
            viewHolder.itemNameTextView = view.findViewById(R.id.item1) // First TextView ID
            viewHolder.anotherTextView = view.findViewById(R.id.item2) // Second TextView ID
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        val item = getItem(position)
        viewHolder.itemNameTextView.text = item?.namabarang // Set text for the first TextView
        viewHolder.anotherTextView.text = item?.namabarang // Set text for the second TextView, adjust as per your data

        return view
    }


    private class ViewHolder {
        lateinit var itemNameTextView: TextView
        lateinit var anotherTextView: TextView // Rename as per your requirement
    }


}