package com.example.load_view.presentation.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Picture(
    val id: Int,
    val url: String
) : Parcelable