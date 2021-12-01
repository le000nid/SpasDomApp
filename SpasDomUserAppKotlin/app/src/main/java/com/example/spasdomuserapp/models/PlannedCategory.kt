package com.example.spasdomuserapp.models

data class PlannedCategory (
    val label: String,
    val drawable: Int,
    val listLvl2: List<PlannedCategoryLvl2>?)