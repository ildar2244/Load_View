package com.example.load_view.presentation.images_list

import androidx.lifecycle.*
import com.example.load_view.core.Response
import com.example.load_view.data.ApiClient
import com.example.load_view.data.PicsumImagesRepositoryImpl
import com.example.load_view.domain.Image
import com.example.load_view.domain.ImagesListUseCase

class ImagesListViewModel : ViewModel(), LifecycleObserver {

    private val repo = PicsumImagesRepositoryImpl(ApiClient().makeService())
    private val useCase = ImagesListUseCase(repo)
    private val _loading = MutableLiveData<Boolean>()
    private val _images = MutableLiveData<List<Image>>()
    private val _error = MutableLiveData<String>()

    val loading: LiveData<Boolean> = _loading
    val images: LiveData<List<Image>> = _images
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
        useCase(Unit) {
            when (it) {
                is Response.Loading -> _loading.value = true
                is Response.Success -> {
                    _loading.value = false
                    _images.value = it.value
                }
                is Response.Error -> {
                    _loading.value = false
                    _error.value = "Ошибка ${it.exception.message}"
                }
            }
        }
    }
}