package com.azamovhudstc.animalwiki.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.azamovhudstc.animalwiki.R
import com.azamovhudstc.animalwiki.data.remote.response.imagesResponse.ImageItem
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.home_item_rv_image.view.*

class ImagePagAdapter(
    val context: Context,
    val setLongClickListener: ImagesAllAdapter.ContactItemCallBack.SetLongClickListener
) : PagingDataAdapter<ImageItem, ImagePagAdapter.Wh>(ContactItemCallBack) {
    inner class Wh(view: View) : RecyclerView.ViewHolder(view) {
        @SuppressLint("NewApi", "SetTextI18n")
        fun onBind(item: ImageItem, position: Int) {
            val circularProgressDrawable = CircularProgressDrawable(context)
            circularProgressDrawable.strokeWidth = 5f
            circularProgressDrawable.centerRadius = 30f
            circularProgressDrawable.start()
            Glide.with(itemView).load(item.urls.thumb).placeholder(circularProgressDrawable)
                .into(itemView.image_view_home)
            itemView.setOnClickListener {
                setLongClickListener.onClick(item)
            }
        }
    }

    object ContactItemCallBack :
        DiffUtil.ItemCallback<com.azamovhudstc.animalwiki.data.remote.response.imagesResponse.ImageItem>() {
        override fun areItemsTheSame(
            oldItem: ImageItem,
            newItem: ImageItem
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: ImageItem,
            newItem: ImageItem
        ): Boolean {
            return oldItem == newItem
        }


        interface SetLongClickListener {
            fun onClick(item: ImageItem)
        }

    }

    override fun onBindViewHolder(holder: Wh, position: Int) {
        if (getItem(position) != null) {
            holder.onBind(getItem(position)!!, position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Wh {
        return Wh(
            LayoutInflater.from(parent.context).inflate(R.layout.home_item_rv_image, parent, false)
        )
    }

}