package com.example.vedacompanionapp.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: FavoriteItem): Long

    @Delete
    fun delete(item: FavoriteItem): Int

    @Query("DELETE FROM favorites WHERE id = :id")
    fun deleteById(id: String): Int

    @Query("SELECT * FROM favorites ORDER BY id ASC")
    fun getAllFavorites(): LiveData<List<FavoriteItem>>
}
