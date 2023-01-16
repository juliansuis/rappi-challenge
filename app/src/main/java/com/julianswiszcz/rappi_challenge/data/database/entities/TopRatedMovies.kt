package com.julianswiszcz.rappi_challenge.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "top_rated_movies")
data class TopRatedMovies(
    @PrimaryKey val id: Int
)
