package com.surelabs.indonesia.recyclerviewpagination.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.surelabs.indonesia.recyclerviewpagination.R
import kotlinx.android.synthetic.main.item_adapter.view.*

class AdapterSimple(private val inputList: MutableList<String>) :
    RecyclerView.Adapter<AdapterSimple.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val item = itemView.item
        fun onBindItem(item: String) {
            this.item.text = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_adapter, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBindItem(item = inputList[position])
    }

    override fun getItemCount(): Int = inputList.size

}
