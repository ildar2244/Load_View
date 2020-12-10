package com.example.load_view.presentation.pictures_list

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.load_view.R
import com.example.load_view.presentation.model.Picture
import kotlinx.android.synthetic.main.item_picture.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class PictureViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    companion object {
        fun from(parent: ViewGroup): PictureViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_picture, parent, false)
            return PictureViewHolder(view)
        }
    }

    fun bind(picture: Picture) {
        with(itemView) {

            CoroutineScope(Dispatchers.Main).launch {
                val uri = "https://picsum.photos/${picture.id}/100"
                val bitmap = downloadImage(uri)
                iv_item_picture.setImageBitmap(bitmap)
            }
        }
    }

    private suspend fun downloadImage(imageUrl: String): Bitmap? {
        var bitmap: Bitmap? = null
        withContext(Dispatchers.IO){
            bitmap = try {
                val url = URL(imageUrl)
                val connection: HttpURLConnection = url
                    .openConnection() as HttpURLConnection
                connection.doInput = true
                connection.connect()
                val input: InputStream = connection.inputStream
                BitmapFactory.decodeStream(input)
            } catch (e: IOException) {
                e.printStackTrace()
                null
            }
        }

        return bitmap
    }
}