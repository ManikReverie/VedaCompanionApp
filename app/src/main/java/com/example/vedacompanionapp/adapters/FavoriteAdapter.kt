package com.example.vedacompanionapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.vedacompanionapp.R
import com.example.vedacompanionapp.data.FavoriteItem

class FavoriteAdapter(
    private var favorites: List<FavoriteItem>,
    private val onItemClick: (FavoriteItem) -> Unit,
    private val onDeleteClick: (FavoriteItem) -> Unit
) : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    fun updateData(newFavorites: List<FavoriteItem>) {
        favorites = newFavorites
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_favorite, parent, false)
        return FavoriteViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val item = favorites[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = favorites.size

    inner class FavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvType: TextView = itemView.findViewById(R.id.tvFavoriteType)
        private val tvTitle: TextView = itemView.findViewById(R.id.tvFavoriteTitle)
        private val tvDesc: TextView = itemView.findViewById(R.id.tvFavoriteDesc)
        private val btnDelete: ImageButton = itemView.findViewById(R.id.btnDeleteFavorite)

        fun bind(item: FavoriteItem) {
            tvType.text = item.type
            tvTitle.text = item.title
            tvDesc.text = item.description

            itemView.setOnClickListener {
                onItemClick(item)
            }

            btnDelete.setOnClickListener {
                onDeleteClick(item)
            }
        }
    }
}

