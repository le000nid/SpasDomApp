package com.example.spasdomuserapp.responses

import com.example.spasdomuserapp.models.CategoriesList

data class SectionCategories (
    val title: String,
    val categories: List<CategoriesList>
)