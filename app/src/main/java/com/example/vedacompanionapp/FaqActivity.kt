package com.example.vedacompanionapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vedacompanionapp.adapters.FaqAdapter
import com.example.vedacompanionapp.data.DataRepository
import com.google.android.material.appbar.MaterialToolbar

class FaqActivity : AppCompatActivity() {

    private lateinit var dataRepository: DataRepository
    private lateinit var faqAdapter: FaqAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_faq)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val toolbar: MaterialToolbar = findViewById<MaterialToolbar?>(R.id.toolbarFaq)
            ?: findViewById(R.id.toolbar)
        toolbar.setNavigationOnClickListener {
            finish()
        }

        dataRepository = DataRepository(this)
        val faqs = dataRepository.getFaqs()

        val recyclerView: RecyclerView = findViewById<RecyclerView?>(R.id.rvFaq)
            ?: findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        faqAdapter = FaqAdapter(faqs)
        recyclerView.adapter = faqAdapter
    }
}
