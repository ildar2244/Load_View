package com.example.load_view.data.model

import com.google.gson.annotations.SerializedName

data class PicsumResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("author")
    val author: String,
    @SerializedName("width")
    val width: String,
    @SerializedName("height")
    val height: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("download_url")
    val download_url: String,
)