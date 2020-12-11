package com.example.load_view.data

import com.example.load_view.data.model.PicsumResponse
import retrofit2.http.GET

interface Api {

    @GET("/v2/list?page=2&limit=100")
    suspend fun getImages(): List<PicsumResponse>
}