package com.example.spasdomuserapp.responses

import com.example.spasdomuserapp.models.SectionCategory

data class SectionCategories (
    val title: String,
    val categories: List<SectionCategory>
)