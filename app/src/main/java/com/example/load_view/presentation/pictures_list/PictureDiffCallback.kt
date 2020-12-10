package com.example.load_view.presentation.pictures_list

import androidx.recyclerview.widget.DiffUtil
import com.example.load_view.presentation.model.Picture

class PictureDiffCallback : DiffUtil.ItemCallback<Picture>() {
    override fun areItemsTheSame(oldItem: Picture, newItem: Picture): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Picture, newItem: Picture): Boolean {
        return oldItem == newItem
    }
}