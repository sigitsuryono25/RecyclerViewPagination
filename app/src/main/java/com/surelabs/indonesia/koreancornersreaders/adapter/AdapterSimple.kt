package com.surelabs.indonesia.koreancornersreaders.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.surelabs.e.jsoupbotapps.model.DataItemPost
import com.surelabs.indonesia.koreancornersreaders.R
import kotlinx.android.synthetic.main.item_adapter_list_reader.view.*

class AdapterSimple(private var listData: List<DataItemPost?>?, private val onclick: (DataItemPost?) -> Unit) :
    RecyclerView.Adapter<AdapterSimple.ViewHolder>() {

    override fun getItemCount(): Int = listData?.size ?: 0


    fun refresh(listData: MutableList<DataItemPost?>?) {
        this.listData = listData
        notifyDataSetChanged()
    }

    open class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    inner class ViewHolderContent(itemView: View) : ViewHolder(itemView)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolderContent(layoutInflater.inflate(R.layout.item_adapter_list_reader, null))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = listData?.get(position)
        holder.itemView.judulArtikel.text = data?.title
        holder.itemView.ringkasanArtikel.text = data?.shotArticle
        holder.itemView.datePost.text = data?.datePost
        Glide.with(holder.itemView.context).load(data?.thumbnail).into(holder.itemView.imagePreview)
        holder.itemView.setOnClickListener {
            onclick(data)
        }
    }

}