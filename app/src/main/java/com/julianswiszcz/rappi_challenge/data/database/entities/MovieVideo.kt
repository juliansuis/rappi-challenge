package com.julianswiszcz.rappi_challenge.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_video")
data class MovieVideo(
    @PrimaryKey val key: String,
)
