package com.example.vedacompanionapp

import android.os.Build
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.vedacompanionapp.data.DataRepository
import com.example.vedacompanionapp.data.FavoriteItem
import com.example.vedacompanionapp.data.Project
import com.example.vedacompanionapp.viewmodel.FavoritesViewModel
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.button.MaterialButton

class ProjectDetailActivity : AppCompatActivity() {

    private val viewModel: FavoritesViewModel by viewModels()
    private var currentProject: Project? = null
    private var isFavorite = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_project_detail)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            insets
        }

        val toolbar = findViewById<MaterialToolbar>(R.id.toolbarDetail)
        toolbar.setNavigationOnClickListener {
            finish()
        }

        // Retrieve Project from Intent (either directly or via ID)
        val projectFromIntent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra("EXTRA_PROJECT", Project::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getSerializableExtra("EXTRA_PROJECT") as? Project
        }

        val projectId = intent.getStringExtra("EXTRA_PROJECT_ID")

        currentProject = projectFromIntent ?: if (!projectId.isNullOrBlank()) {
            DataRepository(this).getProjectById(projectId)
        } else null

        if (currentProject == null) {
            Toast.makeText(this, "Project details not found", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        val project = currentProject!!

        // Populate Views
        findViewById<TextView>(R.id.tvDetailTitle).text = project.title
        findViewById<TextView>(R.id.tvDetailDomain).text = project.domain
        findViewById<TextView>(R.id.tvDetailDifficulty).text = project.difficulty
        findViewById<TextView>(R.id.tvDetailDuration).text = project.duration
        findViewById<TextView>(R.id.tvDetailDesc).text = project.description
        findViewById<TextView>(R.id.tvDetailGuidelines).text = project.guidelines
        findViewById<TextView>(R.id.tvDetailPrerequisites).text = project.prerequisites

        val btnBookmark = findViewById<MaterialButton>(R.id.btnBookmark)

        // Observe Room DB favorite status via ViewModel
        viewModel.isFavorite(project.id).observe(this) { favoriteExists ->
            isFavorite = favoriteExists == true
            updateBookmarkButton(btnBookmark, isFavorite)
        }

        btnBookmark.setOnClickListener {
            val item = FavoriteItem(
                id = project.id,
                title = project.title,
                type = "Project",
                description = project.description
            )

            viewModel.toggleFavorite(item, isFavorite)

            val message = if (isFavorite) {
                "Removed from Favorites"
            } else {
                "Saved to Favorites!"
            }
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateBookmarkButton(button: MaterialButton, isBookmarked: Boolean) {
        if (isBookmarked) {
            button.text = getString(R.string.action_bookmarked)
            button.setIconResource(R.drawable.ic_bookmark)
            button.setBackgroundColor(ContextCompat.getColor(this, R.color.veda_navy_light))
        } else {
            button.text = getString(R.string.action_bookmark)
            button.setIconResource(R.drawable.ic_bookmark_border)
            button.setBackgroundColor(ContextCompat.getColor(this, R.color.veda_orange))
        }
    }
}

