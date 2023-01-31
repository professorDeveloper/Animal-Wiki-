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
import kotlinx.android.synthetic.main.item.view.*

class PostsAdapter(private val setLongClickListener: ContactItemCallBack.SetLongClickListener) :
    ListAdapter<WikiPost, PostsAdapter.Wh>(ContactItemCallBack) {

    inner class Wh(view: View) : RecyclerView.ViewHolder(view) {
        @SuppressLint("NewApi", "SetTextI18n")
        fun onBind(contact: WikiPost, position: Int) {
            itemView.postItemImage.setImageResource(contact.wikiImage)
            itemView.textType.text = contact.wikiName
            itemView.item.setOnClickListener {
                setLongClickListener.onClick(contact)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Wh {
        return Wh(LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false))
    }

    override fun onBindViewHolder(holder: Wh, position: Int) {
        holder.onBind(getItem(position), position)
    }

    object ContactItemCallBack : DiffUtil.ItemCallback<WikiPost>() {
        override fun areItemsTheSame(
            oldItem: WikiPost,
            newItem: WikiPost
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: WikiPost,
            newItem: WikiPost
        ): Boolean {
            return oldItem == newItem
        }


        interface SetLongClickListener {
            fun onClick(item: WikiPost)
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
