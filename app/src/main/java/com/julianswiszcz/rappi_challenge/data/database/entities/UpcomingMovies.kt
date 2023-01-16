package com.julianswiszcz.rappi_challenge.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "upcoming_movies")
data class UpcomingMovies(
    @PrimaryKey val id: Int
)
