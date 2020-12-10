package com.example.load_view.data

import com.example.load_view.core.Response
import com.example.load_view.data.model.PicsumResponse
import com.example.load_view.domain.IPicsumImagesRepository
import com.example.load_view.domain.model.Image

class PicsumImagesRepositoryImpl(
    private val api: Api
) : IPicsumImagesRepository {

    override suspend fun downloadImages(): Response<List<Image>> {
        Response.loading<List<Image>>()
        return try {
            val result = api.getImages().map { it.mapToImage() }
            Response.success(result)
        } catch (e: Exception) {
            Response.error(e)
        }
    }

    private fun PicsumResponse.mapToImage() = Image(id= id, url= download_url)
}