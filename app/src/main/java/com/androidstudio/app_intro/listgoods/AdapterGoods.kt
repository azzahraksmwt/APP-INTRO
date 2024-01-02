package com.androidstudio.app_intro.listgoods

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import com.androidstudio.app_intro.R

class AdapterGoods(private val dataList: ArrayList<GoodsData>): RecyclerView.Adapter<AdapterGoods.ViewHolderClass>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_goods, parent, false)
        return ViewHolderClass(itemView)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
        val currentItem = dataList[position]
        holder.rvImage1.setImageResource(currentItem.dataImage)
        holder.rvImage2.setImageResource(currentItem.dataImage)
        holder.rvItem1.text = currentItem.dataItem
        holder.rvItem2.text = currentItem.dataItem
    }

    class ViewHolderClass(itemView: View): RecyclerView.ViewHolder(itemView) {
        val rvImage1:ImageView = itemView.findViewById(R.id.image1)
        val rvImage2:ImageView = itemView.findViewById(R.id.image2)
        val rvItem1:TextView = itemView.findViewById(R.id.item1)
        val rvItem2:TextView = itemView.findViewById(R.id.item2)
    }
}