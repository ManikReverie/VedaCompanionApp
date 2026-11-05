package com.example.vedacompanionapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vedacompanionapp.adapters.DomainAdapter
import com.example.vedacompanionapp.data.DataRepository
import com.google.android.material.appbar.MaterialToolbar

class DomainsActivity : AppCompatActivity() {

    private lateinit var dataRepository: DataRepository
    private lateinit var domainAdapter: DomainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_domains)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val toolbar: MaterialToolbar = findViewById<MaterialToolbar?>(R.id.toolbarDomains)
            ?: findViewById(R.id.toolbar)
        toolbar.setNavigationOnClickListener {
            finish()
        }

        dataRepository = DataRepository(this)
        val domains = dataRepository.getDomains()

        val recyclerView: RecyclerView = findViewById<RecyclerView?>(R.id.rvDomains)
            ?: findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        domainAdapter = DomainAdapter(domains) { selectedDomain ->
            val intent = Intent(this, ProjectsActivity::class.java).apply {
                putExtra("EXTRA_DOMAIN", selectedDomain.name)
            }
            startActivity(intent)
        }
        recyclerView.adapter = domainAdapter
    }
}
