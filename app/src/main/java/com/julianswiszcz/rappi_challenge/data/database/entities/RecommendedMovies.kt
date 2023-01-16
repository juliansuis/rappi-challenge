package com.julianswiszcz.rappi_challenge.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recommended_movies")
data class RecommendedMovies(
    @PrimaryKey val id: Int
)
