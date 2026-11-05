package com.example.vedacompanionapp.data

import android.content.Context
import com.example.vedacompanionapp.R
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader

class DataRepository(private val context: Context) {

    private var cachedDomains: List<Domain>? = null
    private var cachedProjects: List<Project>? = null
    private var cachedFaqs: List<FaqItem>? = null

    private fun loadJsonData(): JSONObject {
        val inputStream = context.resources.openRawResource(R.raw.data)
        val reader = BufferedReader(InputStreamReader(inputStream))
        val jsonString = reader.use { it.readText() }
        return JSONObject(jsonString)
    }

    fun getDomains(): List<Domain> {
        cachedDomains?.let { return it }

        val domains = mutableListOf<Domain>()
        try {
            val root = loadJsonData()
            val jsonArray = root.getJSONArray("domains")
            for (i in 0 until jsonArray.length()) {
                val obj = jsonArray.getJSONObject(i)
                domains.add(
                    Domain(
                        id = obj.optString("id"),
                        name = obj.optString("name"),
                        description = obj.optString("description"),
                        techStack = obj.optString("techStack"),
                        projectCount = obj.optInt("projectCount", 0)
                    )
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        cachedDomains = domains
        return domains
    }

    fun getProjects(): List<Project> {
        cachedProjects?.let { return it }

        val projects = mutableListOf<Project>()
        try {
            val root = loadJsonData()
            val jsonArray = root.getJSONArray("projects")
            for (i in 0 until jsonArray.length()) {
                val obj = jsonArray.getJSONObject(i)
                projects.add(
                    Project(
                        id = obj.optString("id"),
                        title = obj.optString("title"),
                        domain = obj.optString("domain"),
                        duration = obj.optString("duration"),
                        difficulty = obj.optString("difficulty"),
                        description = obj.optString("description"),
                        guidelines = obj.optString("guidelines"),
                        prerequisites = obj.optString("prerequisites")
                    )
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        cachedProjects = projects
        return projects
    }

    fun getProjectsByDomain(domain: String): List<Project> {
        return getProjects().filter { it.domain.equals(domain, ignoreCase = true) }
    }

    fun getProjectById(id: String): Project? {
        return getProjects().firstOrNull { it.id == id }
    }

    fun getFaqs(): List<FaqItem> {
        cachedFaqs?.let { return it }

        val faqs = mutableListOf<FaqItem>()
        try {
            val root = loadJsonData()
            val jsonArray = root.getJSONArray("faqs")
            for (i in 0 until jsonArray.length()) {
                val obj = jsonArray.getJSONObject(i)
                faqs.add(
                    FaqItem(
                        id = obj.optString("id"),
                        question = obj.optString("question"),
                        answer = obj.optString("answer"),
                        category = obj.optString("category")
                    )
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        cachedFaqs = faqs
        return faqs
    }
}

