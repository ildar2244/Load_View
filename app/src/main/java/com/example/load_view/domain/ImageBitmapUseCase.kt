package com.example.load_view.domain

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import javax.inject.Inject

class ImageBitmapUseCase @Inject constructor() {

    private lateinit var imageUrl: String

    fun getImageUrl() = imageUrl

    suspend fun downloadById(imageId: Int): Bitmap? {
        imageUrl = "https://picsum.photos/id/$imageId/400/600"
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