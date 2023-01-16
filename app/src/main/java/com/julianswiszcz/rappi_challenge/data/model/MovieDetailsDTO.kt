package com.julianswiszcz.rappi_challenge.data.model

import com.google.gson.annotations.SerializedName

data class MovieDetailsDTO(
    val id: Int,
    val title: String,
    @SerializedName("backdrop_path") val image: String?,
    @SerializedName("overview") val description: String,
)
