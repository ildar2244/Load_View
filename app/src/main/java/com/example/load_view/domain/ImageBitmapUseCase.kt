package com.example.load_view.domain

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class ImageBitmapUseCase {

    suspend fun download(imageUrl: String): Bitmap? {
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