package com.example.vedacompanionapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vedacompanionapp.adapters.FavoriteAdapter
import com.example.vedacompanionapp.viewmodel.FavoritesViewModel
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar

class FavoritesActivity : AppCompatActivity() {

    private val viewModel: FavoritesViewModel by viewModels()
    private lateinit var favoriteAdapter: FavoriteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_favorites)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val toolbar: MaterialToolbar = findViewById<MaterialToolbar?>(R.id.toolbarFavorites)
            ?: findViewById(R.id.toolbar)
        toolbar.setNavigationOnClickListener {
            finish()
        }

        val tvFavoriteCount = findViewById<TextView?>(R.id.tvFavoriteCount)
            ?: findViewById<TextView?>(R.id.tvListCount)
        val rvFavorites: RecyclerView = findViewById<RecyclerView?>(R.id.rvFavorites)
            ?: findViewById(R.id.recyclerView)
        val layoutEmptyState = findViewById<View>(R.id.layoutEmptyState)
        val btnExploreProjects = findViewById<MaterialButton?>(R.id.btnExploreProjects)

        btnExploreProjects?.setOnClickListener {
            startActivity(Intent(this, ProjectsActivity::class.java))
            finish()
        }

        rvFavorites.layoutManager = LinearLayoutManager(this)

        favoriteAdapter = FavoriteAdapter(
            favorites = emptyList(),
            onItemClick = { item ->
                if (item.type.equals("Project", ignoreCase = true)) {
                    val intent = Intent(this, ProjectDetailActivity::class.java).apply {
                        putExtra("EXTRA_PROJECT_ID", item.id)
                    }
                    startActivity(intent)
                }
            },
            onDeleteClick = { item ->
                viewModel.delete(item)
                Snackbar.make(findViewById(R.id.main), "Removed \"${item.title}\"", Snackbar.LENGTH_LONG)
                    .setAction("Undo") {
                        viewModel.insert(item)
                    }
                    .show()
            }
        )
        rvFavorites.adapter = favoriteAdapter

        // Observe LiveData from ViewModel
        viewModel.allFavorites.observe(this) { favoritesList ->
            if (favoritesList.isNullOrEmpty()) {
                layoutEmptyState.visibility = View.VISIBLE
                rvFavorites.visibility = View.GONE
                tvFavoriteCount?.text = "0 Saved"
            } else {
                layoutEmptyState.visibility = View.GONE
                rvFavorites.visibility = View.VISIBLE
                tvFavoriteCount?.text = "${favoritesList.size} Saved"
                favoriteAdapter.updateData(favoritesList)
            }
        }
    }
}
