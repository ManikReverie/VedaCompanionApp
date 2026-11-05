package com.example.vedacompanionapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.vedacompanionapp.data.AppDatabase
import com.example.vedacompanionapp.data.FavoriteItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoritesViewModel(application: Application) : AndroidViewModel(application) {

    private val favoriteDao = AppDatabase.getDatabase(application).favoriteDao()
    val allFavorites: LiveData<List<FavoriteItem>> = favoriteDao.getAllFavorites()

    fun insert(item: FavoriteItem) {
        viewModelScope.launch(Dispatchers.IO) {
            favoriteDao.insert(item)
        }
    }

    fun delete(item: FavoriteItem) {
        viewModelScope.launch(Dispatchers.IO) {
            favoriteDao.delete(item)
        }
    }

    fun deleteById(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            favoriteDao.deleteById(id)
        }
    }

    fun isFavorite(id: String): LiveData<Boolean> {
        return allFavorites.map { list -> list.any { it.id == id } }
    }

    fun toggleFavorite(item: FavoriteItem, isCurrentlyFavorite: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            if (isCurrentlyFavorite) {
                favoriteDao.deleteById(item.id)
            } else {
                favoriteDao.insert(item)
            }
        }
    }
}
