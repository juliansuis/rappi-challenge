package com.julianswiszcz.rappi_challenge.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_table")
data class MovieEntity(
    @PrimaryKey val id: Int,
    val image: String?,
)
