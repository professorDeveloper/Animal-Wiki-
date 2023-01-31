package com.azamovhudstc.animalwiki.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.azamovhudstc.animalwiki.R
import com.azamovhudstc.animalwiki.data.remote.response.playListResponse.Item
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.view_all_item.view.*

class ViewAllPlayListAdapter(var viewAllAdapterClicker: ContactItemCallBack.ViewAllAdapterClicker) : ListAdapter<Item, ViewAllPlayListAdapter.Wh>(ContactItemCallBack) {

    inner class Wh(view: View) : RecyclerView.ViewHolder(view) {
        @SuppressLint("NewApi", "SetTextI18n")
        fun onBind(contact: Item, position: Int) {
            if (contact.snippet.thumbnails.standard != null) Picasso.get()
                .load(contact.snippet.thumbnails.standard.url)
                .into(itemView.viewAllImage)
            else if (contact.snippet.thumbnails.medium != null) Picasso.get()
                .load(contact.snippet.thumbnails.medium.url)
                .into(itemView.viewAllImage)
            else if (contact.snippet.thumbnails.default != null) Picasso.get()
                .load(contact.snippet.thumbnails.default.url)
                .into(itemView.viewAllImage)
            itemView.setOnClickListener {
                viewAllAdapterClicker.onClick(contact)
            }
            itemView.viewAllVideTitle.text = contact.snippet.title
            itemView.viewAllDate.text = contact.snippet.publishedAt
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Wh {
        return Wh(
            LayoutInflater.from(parent.context).inflate(R.layout.view_all_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: Wh, position: Int) {
        holder.onBind(getItem(position), position)
    }

    object ContactItemCallBack : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(
            oldItem: Item,
            newItem: Item
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: Item,
            newItem: Item
        ): Boolean {
            return oldItem.id == newItem.id
        }


        interface ViewAllAdapterClicker {
            fun onClick(item: Item)
        }

    }

}
