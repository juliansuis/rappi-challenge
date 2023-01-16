package com.julianswiszcz.rappi_challenge.data.model

import com.google.gson.annotations.SerializedName

data class MovieDTO(
    val id: Int,
    @SerializedName("backdrop_path") val image: String?,
)
