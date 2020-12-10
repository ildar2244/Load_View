package com.example.load_view.presentation.pictures_list

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.load_view.presentation.model.Picture


class PicturesAdapter(
    val listener: (Picture) -> Unit
) : ListAdapter<Picture, PictureViewHolder>(PictureDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PictureViewHolder {
        return PictureViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: PictureViewHolder, position: Int) {
        holder.apply {
            bind(getItem(position))
            itemView.setOnClickListener { listener(getItem(position)) }
        }

    }
}
