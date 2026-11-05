package com.example.vedacompanionapp

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vedacompanionapp.adapters.ProjectAdapter
import com.example.vedacompanionapp.data.DataRepository
import com.google.android.material.appbar.MaterialToolbar

class ProjectsActivity : AppCompatActivity() {

    private lateinit var dataRepository: DataRepository
    private lateinit var projectAdapter: ProjectAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_projects)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val toolbar: MaterialToolbar = findViewById<MaterialToolbar?>(R.id.toolbarProjects)
            ?: findViewById(R.id.toolbar)
        toolbar.setNavigationOnClickListener {
            finish()
        }

        val tvFilterHeader = findViewById<TextView?>(R.id.tvProjectFilterHeader)
            ?: findViewById<TextView?>(R.id.tvListSubtitle)
        val tvProjectCount = findViewById<TextView?>(R.id.tvProjectCount)
            ?: findViewById<TextView?>(R.id.tvListCount)
        val recyclerView: RecyclerView = findViewById<RecyclerView?>(R.id.rvProjects)
            ?: findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        dataRepository = DataRepository(this)

        val selectedDomain = intent.getStringExtra("EXTRA_DOMAIN")
        val projects = if (!selectedDomain.isNullOrBlank()) {
            toolbar.title = selectedDomain
            tvFilterHeader?.text = "Filtered by $selectedDomain"
            dataRepository.getProjectsByDomain(selectedDomain)
        } else {
            toolbar.title = getString(R.string.title_projects)
            tvFilterHeader?.text = "Hands-on capstone and milestone assignments"
            dataRepository.getProjects()
        }

        tvProjectCount?.text = "${projects.size} Available"

        projectAdapter = ProjectAdapter(projects) { project ->
            val intent = Intent(this, ProjectDetailActivity::class.java).apply {
                putExtra("EXTRA_PROJECT", project)
            }
            startActivity(intent)
        }
        recyclerView.adapter = projectAdapter
    }
}
