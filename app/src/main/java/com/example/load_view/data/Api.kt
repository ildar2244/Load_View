package com.example.load_view.data

import retrofit2.http.GET

interface Api {

    @GET("/v2/list?page=2&limit=100")
    suspend fun getImages(): List<PicsumResponse>
}