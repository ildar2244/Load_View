package com.example.load_view.domain

import com.example.load_view.core.Response
import com.example.load_view.core.UseCase
import com.example.load_view.domain.model.Image
import javax.inject.Inject

class ImagesListUseCase @Inject constructor(
    private val repository: IPicsumImagesRepository
) : UseCase<List<Image>, Unit>() {

    override suspend fun run(params: Unit): Response<List<Image>> {
        return repository.downloadImages()
    }

}