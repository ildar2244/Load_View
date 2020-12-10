package com.example.load_view.domain

import com.example.load_view.core.Response

interface IPicsumImagesRepository {

    suspend fun downloadImages(): Response<List<Image>>
}