package com.julianswiszcz.rappi_challenge.data.model

import com.julianswiszcz.rappi_challenge.data.database.entities.MovieVideo

data class RequestResponse(val results: List<MovieDTO>)

data class RequestResponseVideo(val results: List<MovieVideo>)
