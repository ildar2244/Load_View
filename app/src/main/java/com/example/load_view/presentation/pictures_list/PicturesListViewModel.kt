package com.example.load_view.presentation.pictures_list

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.*
import com.example.load_view.core.Response
import com.example.load_view.data.ApiClient
import com.example.load_view.data.PicsumImagesRepositoryImpl
import com.example.load_view.domain.model.Image
import com.example.load_view.domain.ImagesListUseCase
import com.example.load_view.presentation.model.Picture
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import javax.inject.Inject

class PicturesListViewModel @Inject constructor(
    private val useCase: ImagesListUseCase
) : ViewModel(), LifecycleObserver {

    //private val repo = PicsumImagesRepositoryImpl(ApiClient().makeService())
    //private val useCase = ImagesListUseCase(repo)
    private val _loading = MutableLiveData<Boolean>()
    private val _images = MutableLiveData<List<Picture>>()
    private val _error = MutableLiveData<String>()

    val loading: LiveData<Boolean> = _loading
    val images: LiveData<List<Picture>> = _images
    val error: LiveData<String> = _error

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        getImagesList()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() {
        useCase.unsubscribe()
    }

    private fun getImagesList() {
        useCase(Unit) { response ->
            when (response) {
                is Response.Loading -> _loading.value = true
                is Response.Success -> {
                    _loading.value = false
                    _images.value = response.value.map { it.mapToPicture() }
                }
                is Response.Error -> {
                    _loading.value = false
                    _error.value = "Ошибка ${response.exception.message}"
                }
            }
        }
    }

    private fun Image.mapToPicture() = Picture(id, url)

    private fun downloadImageById(picId: Int) {
        viewModelScope.launch {
            val uri = "https://picsum.photos/$picId/100"
            val bitmap = downloadImage(uri)
            withContext(Dispatchers.Main) {
                bitmap
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