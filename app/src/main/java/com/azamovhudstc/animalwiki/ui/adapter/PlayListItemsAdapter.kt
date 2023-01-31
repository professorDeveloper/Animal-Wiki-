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
import kotlinx.android.synthetic.main.main_item.view.*

class PlayListItemsAdapter(var playListsItemOnClick: ContactItemCallBack.PlayListsItemOnClick) : ListAdapter<Item, PlayListItemsAdapter.Wh>(ContactItemCallBack) {

    inner class Wh(view: View) : RecyclerView.ViewHolder(view) {
        @SuppressLint("NewApi", "SetTextI18n")
        fun onBind(contact: Item, position: Int) {
            if (contact.snippet.thumbnails.standard != null) Picasso.get()
                .load(contact.snippet.thumbnails.standard.url)
                .into(itemView.big_image)
            else if (contact.snippet.thumbnails.medium != null) Picasso.get()
                .load(contact.snippet.thumbnails.medium.url)
                .into(itemView.big_image)
            else if (contact.snippet.thumbnails.default != null) Picasso.get()
                .load(contact.snippet.thumbnails.default.url)
                .into(itemView.big_image)
            itemView.setOnClickListener {
                playListsItemOnClick.onClick(contact)
            }
            itemView.titleVideo.text = contact.snippet.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Wh {
        return Wh(LayoutInflater.from(parent.context).inflate(R.layout.main_item, parent, false))
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
            return oldItem == newItem
        }


        interface PlayListsItemOnClick {
            fun onClick(item: Item)
        }

    }

}
//package com.azamovhudstc.quranuz.ui.adapter
//
//import android.content.Context
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//import com.azmovhudstc.playmarketcloneui.R
//import com.azmovhudstc.playmarketcloneui.model.GeneralData
//import kotlinx.android.synthetic.main.general_item.view.*
//
//class PlayListsAdapter(var arrayList: List<GeneralData>, var context:Context) :
//    RecyclerView.Adapter<PlayListsAdapter.Wh>() {
//    inner class Wh(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        fun generalData(generalData: GeneralData) {
//            itemView.title_general.text=generalData.title
//            var topsAdapter=TopsAdapter(list = generalData.appList )
//            itemView.rv_inside.layoutManager=LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false,)
//            itemView.rv_inside.adapter=topsAdapter
//        }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Wh {
//        return Wh(LayoutInflater.from(parent.context).inflate(R.layout.general_item, parent, false))
//    }
//
//    override fun onBindViewHolder(holder: Wh, position: Int) {
//        holder.generalData(arrayList[position]!!)
//    }
//
//    override fun getItemCount(): Int = arrayList.size
//
//}
