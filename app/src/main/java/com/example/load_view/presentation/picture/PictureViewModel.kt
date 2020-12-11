package com.example.load_view.presentation.picture

import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.*
import com.example.load_view.domain.ImageBitmapUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PictureViewModel @Inject constructor(
    val useCase: ImageBitmapUseCase
) : ViewModel() {

    private val _imageBitmap = MutableLiveData<Bitmap>()
    private val _imageUrl = MutableLiveData<String>()

    val imageBitmap: LiveData<Bitmap> = _imageBitmap
    val imageUrl: LiveData<String> = _imageUrl

    fun getPictureBitmap(pictureId: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                Log.d("9999", "getPictureBitmap: ${useCase.downloadById(pictureId)}")
                _imageBitmap.value = useCase.downloadById(pictureId)
                _imageUrl.value = useCase.getImageUrl()
            }
        }
    }
}