package com.example.spasdomuserapp.responses

import com.example.spasdomuserapp.models.SectionCategory

data class SectionCategoriesResponse (
    val title: String,
    val categories: List<SectionCategory>
)