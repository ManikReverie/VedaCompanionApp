package com.example.vedacompanionapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class FavoriteItem(
    @PrimaryKey
    val id: String,
    val title: String,
    val type: String,
    val description: String = ""
)
