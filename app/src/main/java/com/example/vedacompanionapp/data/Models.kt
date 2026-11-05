package com.example.vedacompanionapp.data

import java.io.Serializable

data class Domain(
    val id: String,
    val name: String,
    val description: String,
    val techStack: String,
    val projectCount: Int
) : Serializable

data class Project(
    val id: String,
    val title: String,
    val domain: String,
    val duration: String,
    val difficulty: String,
    val description: String,
    val guidelines: String,
    val prerequisites: String
) : Serializable

data class FaqItem(
    val id: String,
    val question: String,
    val answer: String,
    val category: String
) : Serializable

