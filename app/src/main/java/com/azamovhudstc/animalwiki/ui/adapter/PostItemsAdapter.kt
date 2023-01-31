package com.azamovhudstc.animalwiki.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.azamovhudstc.animalwiki.R
import com.azamovhudstc.animalwiki.data.local.models.WikiPost
import com.azamovhudstc.animalwiki.data.local.models.WikiPostData
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item.view.*
import kotlinx.android.synthetic.main.item_data.view.*

class PostItemsAdapter(private val setLongClickListener: ContactItemCallBack.SetLongClickListener) :
    ListAdapter<WikiPostData, PostItemsAdapter.Wh>(ContactItemCallBack) {

    inner class Wh(view: View) : RecyclerView.ViewHolder(view) {
        @SuppressLint("NewApi", "SetTextI18n")
        fun onBind(contact: WikiPostData, position: Int) {
            itemView.postItem.isClickable = true
            itemView.postItemTitle.text = contact.title
            itemView.postItemDes.text = contact.des
            Glide.with(itemView).load(contact.imgData).into(itemView.postItemIcon)
            itemView.postItem.setOnClickListener {
                setLongClickListener.onClick(contact)
                itemView.postItem.isClickable = false
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Wh {
        return Wh(LayoutInflater.from(parent.context).inflate(R.layout.item_data, parent, false))
    }

    override fun onBindViewHolder(holder: Wh, position: Int) {
        holder.onBind(getItem(position), position)
    }

    object ContactItemCallBack : DiffUtil.ItemCallback<WikiPostData>() {
        override fun areItemsTheSame(
            oldItem: WikiPostData,
            newItem: WikiPostData
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: WikiPostData,
            newItem: WikiPostData
        ): Boolean {
            return oldItem.title == newItem.title
        }


        interface SetLongClickListener {
            fun onClick(item: WikiPostData)
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
