package com.example.vedacompanionapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.card.MaterialCardView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, 0, systemBars.right, systemBars.bottom)
            insets
        }

        val cardDomains = findViewById<MaterialCardView>(R.id.cardDomains)
        val cardProjects = findViewById<MaterialCardView>(R.id.cardProjects)
        val cardFaq = findViewById<MaterialCardView>(R.id.cardFaq)
        val cardFavorites = findViewById<MaterialCardView>(R.id.cardFavorites)

        cardDomains.setOnClickListener {
            startActivity(Intent(this, DomainsActivity::class.java))
        }

        cardProjects.setOnClickListener {
            startActivity(Intent(this, ProjectsActivity::class.java))
        }

        cardFaq.setOnClickListener {
            startActivity(Intent(this, FaqActivity::class.java))
        }

        cardFavorites.setOnClickListener {
            startActivity(Intent(this, FavoritesActivity::class.java))
        }
    }
}
