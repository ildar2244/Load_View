package com.example.load_view.domain

import com.example.load_view.core.Response
import com.example.load_view.domain.model.Image

interface IPicsumImagesRepository {

    suspend fun downloadImages(): Response<List<Image>>
}